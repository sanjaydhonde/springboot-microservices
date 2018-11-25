package com.sanjayd.productservice.json.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetAllOrdersResponse {

	private ArrayList<OrderJsonModel> orderList;
	private String type="GET_ALL_ORDERS";
	private String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public ArrayList<OrderJsonModel> getOrderList() {
		return orderList;
	}
	public void setOrderList(ArrayList<OrderJsonModel> orderList) {
		this.orderList = orderList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}

