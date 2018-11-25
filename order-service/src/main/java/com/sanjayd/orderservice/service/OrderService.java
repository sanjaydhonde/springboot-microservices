package com.sanjayd.orderservice.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sanjayd.orderservice.controller.OrderServiceController;
import com.sanjayd.orderservice.dao.IGenericDao;
import com.sanjayd.orderservice.domain.Address;
import com.sanjayd.orderservice.domain.CustomerOrderProducts;
import com.sanjayd.orderservice.domain.CustomerOrders;
import com.sanjayd.orderservice.domain.CustomerOrdersDelivery;
import com.sanjayd.orderservice.domain.CustomerPaymentMethods;
import com.sanjayd.orderservice.domain.Customers;
import com.sanjayd.orderservice.domain.ElectronicContact;
import com.sanjayd.orderservice.domain.EmbeddedOrderProductId;
import com.sanjayd.orderservice.domain.Products;
import com.sanjayd.orderservice.domain.RefDeliveryStatusCodes;
import com.sanjayd.orderservice.domain.RefOrderStatusCodes;
import com.sanjayd.orderservice.domain.RefPaymentMethods;
import com.sanjayd.orderservice.gson.provider.GsonProvider;
import com.sanjayd.orderservice.json.model.AddOrderRequest;
import com.sanjayd.orderservice.json.model.AddressJsonModel;
import com.sanjayd.orderservice.json.model.GetAllOrdersRequest;
import com.sanjayd.orderservice.json.model.GetAllOrdersResponse;
import com.sanjayd.orderservice.json.model.OrderJsonModel;
import com.sanjayd.orderservice.json.model.ProductJsonModel;
import com.sanjayd.orderservice.utility.EmailSender;

@Service
@Transactional(rollbackFor={Exception.class},propagation = Propagation.REQUIRES_NEW)
public class OrderService {
	
	@Autowired
    private IGenericDao<CustomerOrders> customerOrdersDao;
	
	@Autowired
    private IGenericDao<CustomerOrderProducts> customerOrderProducts;	
	
	@Autowired
    private IGenericDao<CustomerPaymentMethods> customerPaymentMethodsDao;
	
	@Autowired
    private IGenericDao<CustomerOrdersDelivery> customerOrdersDeliveryDao;
	
	@Autowired
    private IGenericDao<Address> addressDao;
	
	@Autowired
    private IGenericDao<Products> productsDao;
	
	@Autowired
    private IGenericDao<ElectronicContact> electronicContactDao;
  
    @Autowired
    private IGenericDao<RefOrderStatusCodes> refOrderStatusCodes;
    
    @Autowired
    private IGenericDao<RefPaymentMethods> refPaymentMethods;
    
    @Autowired
    private IGenericDao<RefDeliveryStatusCodes> refDeliveryStatusCodes;
    
    @Autowired
    private GsonProvider gsonProvider;
    
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
  
    public String getAllOrders(String json){
    	
    	GetAllOrdersResponse getAllOrderResponse = new GetAllOrdersResponse();
    	ArrayList<OrderJsonModel> allOrders= new ArrayList<OrderJsonModel>();
    	
    	GetAllOrdersRequest jsonOrder= gsonProvider.getGson().fromJson(json, GetAllOrdersRequest.class);

    	String email = jsonOrder.getEmail();
    	logger.info("email="+email);
    	
    	//Use Email id to find Customer.
    	String qlString= "select cust from ElectronicContact ec where ec.email=?1 ";
    	electronicContactDao.setClazz(ElectronicContact.class);
    	List<Object> custs=electronicContactDao.findUsingQuery(qlString, email);
    	if(custs.size()>0){
	    	Customers cust=(Customers)custs.get(0);    	
	    	logger.info("custid="+cust.getId());
	    	
	    	//Use Customer to get orders
	    	String qlString2= "select co from CustomerOrders co where co.cust=?1 ";
	    	customerOrdersDao.setClazz(CustomerOrders.class);
	    	List<Object> orders=customerOrdersDao.findUsingQuery(qlString2, cust);
	    
	    	Iterator<Object> it= orders.iterator();
	    	for(;it.hasNext();){
	    		OrderJsonModel orderJsonModel = new OrderJsonModel();
	    		
	    		CustomerOrders ord= (CustomerOrders)it.next();
	    		
	    		Address deliveryAddress =ord.getDeliveryAddress();
	    		AddressJsonModel addressJson = new AddressJsonModel();
	    		addressJson.setAddLine1(deliveryAddress.getAddLine1());
	    		addressJson.setAddLine2(deliveryAddress.getAddLine2());
	    		addressJson.setAddLine3(deliveryAddress.getAddLine3());
	    		addressJson.setCity(deliveryAddress.getCity());
	    		addressJson.setPin(deliveryAddress.getPin());
	    		addressJson.setState(deliveryAddress.getState());
	    		orderJsonModel.setShippingAddress(addressJson);
	    		
	    		Double totalAmount =ord.getTotalAmount();
	    		orderJsonModel.setTotalAmount(totalAmount);
	    		
	    		long orderId =ord.getId();
	    		orderJsonModel.setOrderId(Long.toString(orderId));
	    		
	    		String paymentStatus= ord.getOrderStatus().getOrderStatusCode().name();
	    		orderJsonModel.setPaymentStatus(paymentStatus);
	    		
	    		String deliveryStatus= ord.getOrderDelivery().getStatusCodes().getDeliveryStatusCodes().name();
	    		orderJsonModel.setDeliveryStatus(deliveryStatus);
	    		
	    		//Use Orders to fetch products
	    		
	        	String qlString3= "select cop from CustomerOrderProducts cop where cop.id.customerOrders=?1 ";
	        	customerOrderProducts.setClazz(CustomerOrderProducts.class);
	        	List<Object> orderProducts=customerOrderProducts.findUsingQuery(qlString3, ord);
	        	Iterator<Object> it2= orderProducts.iterator();
	        	
	        	
	        	List<ProductJsonModel>  prodjsonList= new ArrayList<ProductJsonModel>();
	        	
	        	for(;it2.hasNext();){
	        		CustomerOrderProducts orderProduct= (CustomerOrderProducts)it2.next();
	        		Products prod= orderProduct.getId().getProducts();
	
	        			ProductJsonModel prodJson= new ProductJsonModel();
	        			prodJson.setId(Long.toString(prod.getId()));
	        			prodJson.setName(prod.getName());
	        			prodJson.setCost(prod.getCost());
	        			prodjsonList.add(prodJson);
	        		
	        	}
	        	orderJsonModel.setProducts(prodjsonList);
	    		allOrders.add(orderJsonModel);
	    	}
    	}
    	
    	getAllOrderResponse.setOrderList(allOrders);  	
    	return gsonProvider.getGson().toJson(getAllOrderResponse);
    	
    }
    
    public void addOrder(String json) throws Exception{
    	AddOrderRequest jsonOrder= gsonProvider.getGson().fromJson(json, AddOrderRequest.class);
    	
    	//email, total, shipadd, productlist
    	//AddressJsonModel shippingAddress = jsonOrder.getOrder().getShippingAddress();
    	Double totalAmount = jsonOrder.getOrder().getTotalAmount();
    	
    	List<ProductJsonModel> products= jsonOrder.getOrder().getProducts();
    	String email = jsonOrder.getEmail();
    	logger.info("email="+email);
    	
    	//Use Email id to find Customer.
    	String qlString= "select cust from ElectronicContact ec where ec.email=?1 ";
    	electronicContactDao.setClazz(ElectronicContact.class);
    	List<Object> custs=electronicContactDao.findUsingQuery(qlString, email);
    	if(custs.size() > 0){
	    	Customers cust=(Customers)custs.get(0);    	
	    	logger.info("custid="+cust.getId());
	    	
	    	//1-->Paid
	    	//2-->NotPaid
	    	RefOrderStatusCodes orderStatus = findOneRefOrderStatusCodes(2);//NotPaid
	    	logger.info("orderstatus"+orderStatus.getOrderStatusCode());
	    	
	    	//Customer Delivery Address
	    	Address add = new Address();
			add.setAddLine1(jsonOrder.getOrder().getShippingAddress().getAddLine1());
			add.setAddLine2(jsonOrder.getOrder().getShippingAddress().getAddLine2());
			add.setAddLine3(jsonOrder.getOrder().getShippingAddress().getAddLine3());
			add.setCity(jsonOrder.getOrder().getShippingAddress().getCity());
			add.setState(jsonOrder.getOrder().getShippingAddress().getState());
			add.setPin(jsonOrder.getOrder().getShippingAddress().getPin());
			createAddress(add);
	    	
			//Customer order
	    	CustomerOrders order = new CustomerOrders();
	    	order.setOrderStatus(orderStatus);
	    	order.setTotalAmount(totalAmount);
	    	order.setDeliveryAddress(add);
	    	order.setCust(cust);
	    	createCustomerOrders(order);
	    	
	    	//1-->COD
	    	RefPaymentMethods paymentMethod = findOneRefPaymentMethods(1);//COD
	    	CustomerPaymentMethods paymentMethods  = new CustomerPaymentMethods();
	    	paymentMethods.setPaymentMethods(paymentMethod);
	    	paymentMethods.setCustomer(cust);
	    	//order.setPayment(paymentMethods);
	    	paymentMethods.setOrders(order);
	    	createCustomerPaymentMethods(paymentMethods);
	    	logger.info("pm="+paymentMethod.getId());
	    	
	    	//1-->Delivered
	    	//2-->NotDelivered
	    	RefDeliveryStatusCodes deliveryStatusCode = findOneRefDeliveryStatusCodes(2);//NotDelivered
	    	CustomerOrdersDelivery delivery = new CustomerOrdersDelivery();
	    	delivery.setStatusCodes(deliveryStatusCode);
	    	delivery.setOrders(order);
	    	//order.setOrderDelivery(delivery);
	    	createCustomerOrdersDelivery(delivery);
	    	logger.info("delivery status code"+deliveryStatusCode.getId());
	    	
	    	//updateCustomerOrders(order);
	    	
	    	for(ProductJsonModel prod: products ){
	    		CustomerOrderProducts orderProducts = new CustomerOrderProducts();
	    		prod.getCost();
	    		    		
	    		productsDao.setClazz(Products.class);
	    		Products produ = productsDao.findOne(Long.parseLong(prod.getId()));
	
	    		logger.info("prod= "+produ.getId());
	    		EmbeddedOrderProductId embeddedOrderProductId = new EmbeddedOrderProductId();
	    		embeddedOrderProductId.setProducts(produ);
	    		embeddedOrderProductId.setCustomerOrders(order);
	    		
	    		orderProducts.setQuantity(prod.getQuantity());
	    		orderProducts.setId(embeddedOrderProductId);
	    		
	    		createCustomerOrderProducts(orderProducts);
	    	}
	    	String mailSubject="Your Order Acknowledgement";
	    	String message="<html><body>You have placed order successfully.\n Your OrderId is: "+order.getId()+"</body></html>";
	    	//AmazonSESEmailSender.sendEmail(email, mailSubject, message);
			//EmailSender.sendEmail(email, mailSubject, message);

    	}

    }
    

    public void createCustomerPaymentMethods(final CustomerPaymentMethods entity) {
    	customerPaymentMethodsDao.create(entity);
     }
    
    public void createCustomerOrdersDelivery(final CustomerOrdersDelivery entity) {
    	customerOrdersDeliveryDao.create(entity);
     }
    
    public void createAddress(final Address entity) {
    	addressDao.create(entity);
     }
    
    public void createCustomerOrderProducts(final CustomerOrderProducts entity) {
    	customerOrderProducts.create(entity);
     }
    
    public void createCustomerOrders(final CustomerOrders entity) {
    	customerOrdersDao.create(entity);
     }
    
   	public RefOrderStatusCodes findOneRefOrderStatusCodes(long id){
   		refOrderStatusCodes.setClazz(RefOrderStatusCodes.class);
		return refOrderStatusCodes.findOne(id);
	}
   	
   	public RefPaymentMethods findOneRefPaymentMethods(long id){
   		refPaymentMethods.setClazz(RefPaymentMethods.class);
		return refPaymentMethods.findOne(id);
	}
	
   	public RefDeliveryStatusCodes findOneRefDeliveryStatusCodes(long id){
   		refDeliveryStatusCodes.setClazz(RefDeliveryStatusCodes.class);
		return refDeliveryStatusCodes.findOne(id);
	}
	
    
    
}
