package com.sanjayd.userservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetAllOrdersRequest {

	private String type="GET_ALL_ORDERS";
	private String timestamp;
	private String email;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
