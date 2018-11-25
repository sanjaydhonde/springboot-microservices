package com.sanjayd.productservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetProductCategoriesResponse {

	private List<ProductCategoryJsonModel> categories;
	private String type="GET_PRODUCT_CATEGORIES";
	private String timestamp;
	public List<ProductCategoryJsonModel> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategoryJsonModel> categories) {
		this.categories = categories;
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
