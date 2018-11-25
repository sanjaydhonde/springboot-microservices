package com.sanjayd.orderservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

public class AddProductCategoryRequest {
	
	private String name;
	
	private String descr;
	
	private String parentCatName;
	
	private String type="ADD_PRODUCT_CATEGORY";
	
	private String timestamp;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getParentCatName() {
		return parentCatName;
	}

	public void setParentCatName(String parentCatName) {
		this.parentCatName = parentCatName;
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
