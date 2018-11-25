package com.sanjayd.userservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetProductsResponse {

	private List<ProductJsonModel> products ;
	private List<BannerJsonModel> banners ;
	private List<ProductCategoryJsonModel> productCategories; 
	private String type="GET_PRODUCTS";
	private String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	private String version="4";
	public List<ProductJsonModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJsonModel> products) {
		this.products = products;
	}
	
	public List<BannerJsonModel> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerJsonModel> banners) {
		this.banners = banners;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimestamp() {
		//timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp=timestamp;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version =version;
	}

	public List<ProductCategoryJsonModel> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategoryJsonModel> productCategories) {
		this.productCategories = productCategories;
	}
    
}
