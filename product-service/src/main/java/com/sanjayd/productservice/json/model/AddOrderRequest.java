package com.sanjayd.productservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddOrderRequest {

	private String type="ADD_ORDER";
	private String timestamp;
	private String email;
	private OrderJsonModel order;

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

	public OrderJsonModel getOrder() {
		return order;
	}

	public void setOrder(OrderJsonModel order) {
		this.order = order;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
