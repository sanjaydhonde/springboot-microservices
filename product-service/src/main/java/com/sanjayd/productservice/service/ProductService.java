package com.sanjayd.productservice.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.JsonObject;
import com.sanjayd.productservice.dao.IGenericDao;
import com.sanjayd.productservice.domain.Banners;
import com.sanjayd.productservice.domain.ProductCategories;
import com.sanjayd.productservice.domain.ProductVersion;
import com.sanjayd.productservice.domain.Products;
import com.sanjayd.productservice.domain.URLS;
import com.sanjayd.productservice.gson.provider.GsonProvider;
import com.sanjayd.productservice.json.model.AddProductCategoryRequest;
import com.sanjayd.productservice.json.model.AddProductVersionRequest;
import com.sanjayd.productservice.json.model.AddProductsRequest;
import com.sanjayd.productservice.json.model.BannerJsonModel;
import com.sanjayd.productservice.json.model.GetBannersResponse;
import com.sanjayd.productservice.json.model.GetProductCategoriesResponse;
import com.sanjayd.productservice.json.model.GetProductsResponse;
import com.sanjayd.productservice.json.model.ProductCategoryJsonModel;
import com.sanjayd.productservice.json.model.ProductJsonModel;

@Service
@Transactional(rollbackFor={Exception.class},propagation = Propagation.REQUIRES_NEW)
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	private IGenericDao<Products> productsDao;
    
    private IGenericDao<ProductVersion> productVersionDao;
    
    private IGenericDao<ProductCategories> productCategoriesDao;
    
    private IGenericDao<Banners> bannersDao;
    
    private GsonProvider gsonProvider;

    public ProductService() {
        super();
    }

    // API

    public void addProducts(String json) throws Exception{
    	AddProductsRequest allProducts= gsonProvider.getGson().fromJson(json, AddProductsRequest.class);
    	List<ProductJsonModel> listProducts = allProducts.getProducts();
    	for(ProductJsonModel it:listProducts){
    		ProductVersion ver = null;
    		ProductCategories cat =null;
    		String qlString="select productVersion from ProductVersion productVersion where productVersion.version=?1";
    		List<Object> verList = productVersionDao.findUsingQuery(qlString, it.getVerId());
    		
    		if(! CollectionUtils.isEmpty(verList)){
    			ver= (ProductVersion)verList.get(0);
    		}else{
    			throw new Exception("Can not add product as version do not exist:");
    		}
    		logger.info("Version check done");
    		String qString="select productCategories from ProductCategories productCategories where productCategories.name=?1";
    		List<Object> catList = productCategoriesDao.findUsingQuery(qString, it.getCategoryName());
    		if(! CollectionUtils.isEmpty(catList)){
    			cat= (ProductCategories)catList.get(0);
    		}else{
    			throw new Exception("Can not add product as category do not exist:");
    		}
    		logger.info("Category check done");
  		
    		String fileName= it.getName();
    		String title= it.getTitle();
    		String base64= it.getBase64();
    		writeImageFile(fileName,base64);
    		logger.info("created file on server");
    		
    		Products product= new Products();
    		product.setVer(ver);
    		product.setCategory(cat);
    		product.setName(fileName);
    		product.setTitle(title);
    		
    		product.setCost(it.getCost());
    		product.setDescription(it.getDescription());
    		product.setIsEnabled(it.getIsEnabled());
    		product.setUrl("http://localhost:8080/images/user1/"+fileName);
    	
        	createProduct(product);
        	logger.info("product added to DB.");
    		
    	}

    }
    private void writeImageFile(String fileName,String base64) throws FileNotFoundException, IOException {
    	//JsonObject jsonObject= gsonProvider.getJsonParser().parse(json).getAsJsonObject();
    	//"name":f.name,"cat":cat, "subcat":subcat, "price":price, "descr":descr, "base64" : data

        	byte[] imageByte=Base64.getDecoder().decode(base64.split(",")[1]);
			//new FileOutputStream("/tmp/test/"+fileName).write(imageByte);
        	new FileOutputStream("/opt/tomcat/webapps/images/user1/"+fileName).write(imageByte);

    	
		return ;
    	
    }
    
    public String getBanners(){
    	List<Banners> banners= bannersDao.findAll();
    	GetBannersResponse allBanners = new GetBannersResponse();
    	List<BannerJsonModel> listBannersJsonModels = new ArrayList<BannerJsonModel>();
    	for(Banners it : banners)
    	{
    		BannerJsonModel jsonModel = new BannerJsonModel();
    		jsonModel.setName(it.getName());
    		jsonModel.setDescri(it.getDescri());
    		jsonModel.setEnabled(it.isEnabled());
    		jsonModel.setUrl(it.getUrl());
    		listBannersJsonModels.add(jsonModel);
    	}
    	allBanners.setBanners(listBannersJsonModels);
    	return gsonProvider.getGson().toJson(allBanners);
    }
    
    public String getProducts(){
    	List<Products> products= productsDao.findAll();
    	GetProductsResponse allProducts= new GetProductsResponse();
    	List<ProductJsonModel> listProducts = new ArrayList<ProductJsonModel>();
    	List<BannerJsonModel> listBannersJsonModels = new ArrayList<BannerJsonModel>();
    	List<String> urlList; 
    	
    	//Product Items
    	
    	for(Products it : products)
    	{
    		urlList = new ArrayList<String>();
    		ProductJsonModel prod = new ProductJsonModel();
    		prod.setId(Long.toString(it.getId()));
    		logger.info("id= "+ Long.toString(it.getId()));
    		prod.setCost(it.getCost());
    		prod.setDescription(it.getDescription());
    		prod.setIsEnabled(it.getIsEnabled());
    		prod.setName(it.getName());
    		prod.setCategoryName(it.getCategory().getName());
    		prod.setUrl(it.getUrl());
    		prod.setTitle(it.getTitle());
    		prod.setVerId(it.getVer().getVersion());
    		
    		for(URLS u:it.getUrls()){
    			logger.info("url= "+ u.getUrl());
    			urlList.add(u.getUrl());
    		}
    		prod.setUrls(urlList);
	    	listProducts.add(prod);
    	}
    	allProducts.setProducts(listProducts);
    	
    	//Banners
    	List<Banners> banners= bannersDao.findAll();
    	for(Banners it : banners)
    	{
    		BannerJsonModel jsonModel = new BannerJsonModel();
    		jsonModel.setName(it.getName());
    		jsonModel.setDescri(it.getDescri());
    		jsonModel.setEnabled(it.isEnabled());
    		jsonModel.setUrl(it.getUrl());
    		listBannersJsonModels.add(jsonModel);
    	}
    	allProducts.setBanners(listBannersJsonModels);
    	
    	//Categories
    	List<ProductCategories> categories= productCategoriesDao.findAll();
    	List<ProductCategoryJsonModel> listProductCategories = new ArrayList<ProductCategoryJsonModel>();
    	for(ProductCategories it : categories)
    	{
    		ProductCategoryJsonModel category = new ProductCategoryJsonModel();
    		category.setCategoryName(it.getName());
    		category.setCategoryDescription(it.getDescr());
    		category.setParentCategoryName(it.getParentCatName());
    		category.setCategoryUrl(it.getUrl());
    		listProductCategories.add(category);
    	}
    	allProducts.setProductCategories(listProductCategories);
    	
    	return gsonProvider.getGson().toJson(allProducts);
    }
    
    public String getProductCategories(){
    	List<ProductCategories> categories= productCategoriesDao.findAll();
    	GetProductCategoriesResponse productCategoryResponse= new GetProductCategoriesResponse();
    	List<ProductCategoryJsonModel> listProductCategories = new ArrayList<ProductCategoryJsonModel>();
    	for(ProductCategories it : categories)
    	{
    		ProductCategoryJsonModel category = new ProductCategoryJsonModel();
    		category.setCategoryName(it.getName());
    		category.setCategoryDescription(it.getDescr());
    		category.setParentCategoryName(it.getParentCatName());
    		category.setCategoryUrl(it.getUrl());
    		listProductCategories.add(category);
    	}
    	productCategoryResponse.setCategories(listProductCategories);
    	return gsonProvider.getGson().toJson(productCategoryResponse);
    }
    
    public String getProductSubCategories(String json){

    	GetProductCategoriesResponse productCategoryResponse= new GetProductCategoriesResponse();
    	List<ProductCategoryJsonModel> listProductCategories = new ArrayList<ProductCategoryJsonModel>();
	
		JsonObject jsonObject= gsonProvider.getJsonParser().parse(json).getAsJsonObject();

			String qString="select productCategories from ProductCategories productCategories where productCategories.parentCatName=?1";
			List<Object> catList = productCategoriesDao.findUsingQuery(qString, jsonObject.get("cat").getAsString());
			
		
		    	for(Object it : catList)
		    	{
		    		ProductCategoryJsonModel category = new ProductCategoryJsonModel();
		    		category.setCategoryName(((ProductCategories)it).getName());
		    		listProductCategories.add(category);
		    	}

			productCategoryResponse.setCategories(listProductCategories);
	    	return gsonProvider.getGson().toJson(productCategoryResponse);

    }
    
    public void addProductCategory(String json) throws Exception{
    	AddProductCategoryRequest productCategory= gsonProvider.getGson().fromJson(json, AddProductCategoryRequest.class);
    	List<ProductCategories> cats = productCategoriesDao.findAll();
    	for(ProductCategories cat:cats){
			if(cat != null && cat.getName().equalsIgnoreCase(productCategory.getName())){
				throw new Exception("Can not add category as product category already exist:"+productCategory.getName());
			}
    	}
    	ProductCategories newCategory = new ProductCategories();
    	newCategory.setDescr(productCategory.getDescr());
    	newCategory.setName(productCategory.getName());
    	newCategory.setParentCatName(productCategory.getParentCatName());
    	createProductCategories(newCategory);
    }
    
    public void addProductVersion(String json) throws Exception{
    	AddProductVersionRequest productVersion= gsonProvider.getGson().fromJson(json, AddProductVersionRequest.class);
    	List<ProductVersion> vers = productVersionDao.findAll();
    	for(ProductVersion ver:vers){
			if(ver != null && ver.getVersion() == productVersion.getVersion()){
				throw new Exception("Can not add version as product version already exist:"+productVersion.getVersion());
			}
    	}
    	ProductVersion newVersion = new ProductVersion();
    	newVersion.setVersion(productVersion.getVersion());

    	createProductVersion(newVersion);
    }
    
    public void createProduct(final Products entity) {
    	productsDao.create(entity);
    }
    public void createProductCategories(final ProductCategories entity) {
    	productCategoriesDao.create(entity);
     }
    public void createProductVersion(final ProductVersion entity) {
    	productVersionDao.create(entity);
     }

    @Autowired
    public void setProductsDao(IGenericDao<Products> daoToSet){
    	productsDao = daoToSet;
    	productsDao.setClazz(Products.class);
    }
    @Autowired
    public void setProductVersionDao(IGenericDao<ProductVersion> daoToSet){
    	productVersionDao = daoToSet;
    	productVersionDao.setClazz(ProductVersion.class);
    }
    @Autowired
    public void setProductCategoriesDao(IGenericDao<ProductCategories> daoToSet){
    	productCategoriesDao = daoToSet;
    	productCategoriesDao.setClazz(ProductCategories.class);
    }
    @Autowired
    public void setBannersDao(IGenericDao<Banners> daoToSet) {
		bannersDao = daoToSet;
		bannersDao.setClazz(Banners.class);
	}
    
    @Autowired
    public void setGsonProvider(GsonProvider gsonPro){
    	gsonProvider = gsonPro;
    }


	

}