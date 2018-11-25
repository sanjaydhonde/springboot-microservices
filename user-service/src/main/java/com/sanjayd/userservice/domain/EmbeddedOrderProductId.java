package com.sanjayd.userservice.domain;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class EmbeddedOrderProductId implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private CustomerOrders customerOrders;
	
	@ManyToOne
	private Products products;

	public CustomerOrders getCustomerOrders() {
		return customerOrders;
	}

	public void setCustomerOrders(CustomerOrders customerOrders) {
		this.customerOrders = customerOrders;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}
}
