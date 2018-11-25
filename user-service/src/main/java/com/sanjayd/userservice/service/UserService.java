package com.sanjayd.userservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sanjayd.userservice.dao.IGenericDao;
import com.sanjayd.userservice.domain.Address;
import com.sanjayd.userservice.domain.CustomerAddresses;
import com.sanjayd.userservice.domain.Customers;
import com.sanjayd.userservice.domain.ElectronicContact;
import com.sanjayd.userservice.domain.EmailVerification;
import com.sanjayd.userservice.domain.EmbeddedCustAddrId;
import com.sanjayd.userservice.domain.RefAddressTypes;
import com.sanjayd.userservice.domain.Users;
import com.sanjayd.userservice.gson.provider.GsonProvider;
import com.sanjayd.userservice.json.model.AddUserRequest;
import com.sanjayd.userservice.json.model.LoginRequest;
import com.sanjayd.userservice.json.model.LoginResponse;
import com.sanjayd.userservice.json.model.RegistrationResponse;
//import com.sanjayd.userservice.utility.AmazonSESEmailSender;
import com.sanjayd.userservice.utility.EmailSender;
import com.sanjayd.userservice.utility.PasswordUtility;
import com.sanjayd.userservice.utility.UniqueIDGenerator;

@Service
@Transactional(rollbackFor={Exception.class},propagation = Propagation.REQUIRES_NEW)
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IGenericDao<Customers> customersDao;
    @Autowired
    private IGenericDao<CustomerAddresses> customerAddressesDao;
    @Autowired
    private IGenericDao<ElectronicContact> electronicContactDao;
    @Autowired
    private IGenericDao<Address> addressDao;
    @Autowired
    private IGenericDao<EmailVerification> emailVerificationDao;
    @Autowired
    private IGenericDao<Users> usersDao;
    
    @Autowired
    private IGenericDao<RefAddressTypes> refAddressTypesDao;
    
    @Autowired
    private GsonProvider gsonProvider;

    public UserService() {
        super();
    }

    // API
    
    public String authenticate(String json) throws Exception {
    	LoginResponse response = new LoginResponse();
	    	LoginRequest request= gsonProvider.getGson().fromJson(json, LoginRequest.class);
	    	String email= request.getEmail();
	    	String pass= request.getPass();
	    	response.setAuthenticated(false);
			response.setEmailVerified(false);
    	
	    	if(isEmailVerified(email)){
	    		response.setEmailVerified(true);
	    	}
	    	if(isAuthentic(email,pass)){
	    		response.setAuthenticated(true);   			
	    	}
   	
    	return gsonProvider.getGson().toJson(response);
    }
    
    public boolean isAuthentic(String email, String pass) throws Exception{
    	String qlString= "select u from Users u where u.email=?1 ";
    	usersDao.setClazz(Users.class);
    	List<Object> us=usersDao.findUsingQuery(qlString, email);
    	if(us.size()>0){
    		Users usr=(Users)us.get(0); 
    		return PasswordUtility.check(pass, usr.getSaltedHash());
    	}
    	return false;
    }
    
    public boolean isEmailVerified(String email){
    	String qlString= "select u from EmailVerification u where u.email=?1 ";
    	emailVerificationDao.setClazz(EmailVerification.class);
    	List<Object> us=emailVerificationDao.findUsingQuery(qlString, email);
    	if(us.size()>0){
    		EmailVerification usr=(EmailVerification)us.get(0); 
    		return usr.getIsVerified();
    	}
    	return false;
    }
    
    public String verifyEmail(String email, String token){
    	//check token of given email
    	//update isVerified flag on success and noOfAttempt count in DB
    	//EmailVerificationResponse  response= new EmailVerificationResponse();
    	String qlString= "select u from EmailVerification u where u.email=?1 ";
    	emailVerificationDao.setClazz(EmailVerification.class);
    	List<Object> us=emailVerificationDao.findUsingQuery(qlString, email);
    	if(us.size()>0){
    		EmailVerification usr=(EmailVerification)us.get(0); 
    		if(usr.getToken().equalsIgnoreCase(token))
    		{
    			usr.setIsVerified(true);
    			usr.setAttempts(usr.getAttempts()+1);
    			emailVerificationDao.update(usr);
    			return "<html><body>Email Verification is completed successfully.</body></html>";
    		}else{
    			usr.setAttempts(usr.getAttempts()+1);
    			emailVerificationDao.update(usr);
    		}
    	}
    	return "<html><body>Email Verification is failed.</body></html>";
    }
    
    public  String registration(String json) throws Exception{
    	RegistrationResponse response = new RegistrationResponse();
    	
	    	AddUserRequest jsonCustomer= gsonProvider.getGson().fromJson(json, AddUserRequest.class);
	    	String email=jsonCustomer.getContact().getEmail();
	    	String pass=jsonCustomer.getPass();
	    	
	    	//Use Email id to find Customer.
	    	String qlString= "select cust from ElectronicContact ec where ec.email=?1 ";
	    	electronicContactDao.setClazz(ElectronicContact.class);
	    	List<Object> custs=electronicContactDao.findUsingQuery(qlString, email);
	    	if(custs.size()>0){
		    	Customers cust=(Customers)custs.get(0);    	
		    	logger.info("Existing customer - custid="+cust.getId());
		    	response.setStatus("User Already exist.");
		    	
	    	}else{
	    		logger.info("New Customer");
	    		addCustomer(json);
	    		//create unique token
	    		String token= UniqueIDGenerator.getUniqueID();
	        	
	        	//store token in DB and set isVerified=false, 
	    		EmailVerification verification = new EmailVerification();
	    		verification.setEmail(email);
	    		verification.setToken(token);
	    		verification.setIsVerified(false);
	    		createEmailVerification(verification);
	    		
	    		Users users = new Users();
	    		users.setEmail(email);
				users.setSaltedHash(PasswordUtility.getSaltedHash(pass));
	    		createUsers(users);
	    		
	    		//send link to email
	    		//EmailSender.sendVerifyEmail(email, token);
	    		//AmazonSESEmailSender.sendVerifyEmail(email, token);
	    		response.setStatus("Email is Sent to your email address. Kindly verify email address.");
	    	}
	  
    	return gsonProvider.getGson().toJson(response);

    }

    public void addCustomer(String json){
		AddUserRequest jsonCustomer= gsonProvider.getGson().fromJson(json, AddUserRequest.class);
    	
		Customers cust = new Customers();
		cust.setFname(jsonCustomer.getFname());
		cust.setLname(jsonCustomer.getLname());
		cust.setTitle(jsonCustomer.getTitle());
		//create unique customer id
		
		createCustomer(cust);
		
		ElectronicContact contact = new ElectronicContact();
		contact.setEmail(jsonCustomer.getContact().getEmail());
		contact.setPhone(jsonCustomer.getContact().getPhone());
		contact.setCust(cust);
		createElectronicContact(contact);
		
		Address add = new Address();
		add.setAddLine1(jsonCustomer.getAddress().getAddLine1());
		add.setAddLine2(jsonCustomer.getAddress().getAddLine2());
		add.setAddLine3(jsonCustomer.getAddress().getAddLine3());
		add.setCity(jsonCustomer.getAddress().getCity());
		add.setState(jsonCustomer.getAddress().getState());
		add.setPin(jsonCustomer.getAddress().getPin());
		createAddress(add);
		
		//1L --> Home
		RefAddressTypes addType =findOneRefAddressTypes(1L);
		
		EmbeddedCustAddrId id = new EmbeddedCustAddrId();
		id.setAddr(add);
		id.setCust(cust);
		
		CustomerAddresses custAdd = new CustomerAddresses();
		custAdd.setId(id);
		custAdd.setAddressType(addType);
		createCustomerAddresses(custAdd);
    }
    
    public void createUsers(final Users entity){
    	usersDao.create(entity);
    }
    
    public void createEmailVerification(final EmailVerification entity){
    	emailVerificationDao.create(entity);
    }
    
    public void createCustomer(final Customers entity) {
    	customersDao.create(entity);
    }
    public void createElectronicContact(final ElectronicContact entity) {
    	electronicContactDao.create(entity);
     }
    public void createAddress(final Address entity) {
    	addressDao.create(entity);
     }
    public void createCustomerAddresses(final CustomerAddresses entity) {
    	customerAddressesDao.create(entity);
     }
    
    public void setCustomerAddresses(IGenericDao<CustomerAddresses> daoToSet){
    	customerAddressesDao = daoToSet;
    	customerAddressesDao.setClazz(CustomerAddresses.class);
    }
    
    public void setCustomersDao(IGenericDao<Customers> daoToSet){
    	customersDao = daoToSet;
    	customersDao.setClazz(Customers.class);
    	
    }
    public void setEmailVerificationDao(IGenericDao<EmailVerification> daoToSet){
    	emailVerificationDao = daoToSet;
    	emailVerificationDao.setClazz(EmailVerification.class);
    }
    
    public void setUsersDao(IGenericDao<Users> daoToSet){
    	usersDao = daoToSet;
    	usersDao.setClazz(Users.class);
    }
        
    public void setElectronicContactDao(IGenericDao<ElectronicContact> daoToSet){
    	electronicContactDao = daoToSet;
    	electronicContactDao.setClazz(ElectronicContact.class);
    }
    public void setAddressDao(IGenericDao<Address> daoToSet){
    	addressDao = daoToSet;
    	addressDao.setClazz(Address.class);
    }
    
    public void setGsonProvider(GsonProvider gsonPro){
    	gsonProvider = gsonPro;
    }

	public void setRefAddressTypesDao(IGenericDao<RefAddressTypes> refAddressTypes) {
		refAddressTypesDao = refAddressTypes;
		refAddressTypesDao.setClazz(RefAddressTypes.class);
	}
	
	public RefAddressTypes findOneRefAddressTypes(long id){
		refAddressTypesDao.setClazz(RefAddressTypes.class);
		return refAddressTypesDao.findOne(id);
	}
}