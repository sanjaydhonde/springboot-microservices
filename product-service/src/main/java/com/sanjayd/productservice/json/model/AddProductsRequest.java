package com.sanjayd.productservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddProductsRequest {
	
	private List<ProductJsonModel> products ;

	private String type="ADD_PRODUCTS";
	
	private String timestamp;
	
	public List<ProductJsonModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJsonModel> products) {
		this.products = products;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimestamp() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp=timestamp;
	}

}
