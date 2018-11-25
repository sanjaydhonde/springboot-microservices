package com.sanjayd.userservice.json.model;

import java.util.List;

public class OrderJsonModel {
	//private String email;
	private double totalAmount;
	private String deliveryStatus;
	private String paymentStatus;
	private String orderId;
	private AddressJsonModel shippingAddress;
	private List<ProductJsonModel> products;

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public AddressJsonModel getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(AddressJsonModel shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<ProductJsonModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJsonModel> products) {
		this.products = products;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


}
