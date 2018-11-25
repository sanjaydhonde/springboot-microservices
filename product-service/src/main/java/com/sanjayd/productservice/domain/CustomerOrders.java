package com.sanjayd.productservice.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrders implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	private Customers cust;
	
	@OneToOne( mappedBy="orders",cascade=CascadeType.ALL)
	private CustomerOrdersDelivery orderDelivery;
	
	@OneToOne( mappedBy="orders",cascade=CascadeType.ALL)
	private CustomerPaymentMethods payment;
	
	@OneToOne
	private Address deliveryAddress;
	
	private double totalAmount;
	
	public Customers getCust() {
		return cust;
	}

	public void setCust(Customers cust) {
		this.cust = cust;
	}
	
	@ManyToOne 
	@JoinColumn(name="orderStatus" ,referencedColumnName="orderStatusCode", nullable = false)
	private RefOrderStatusCodes orderStatus;

	public CustomerOrdersDelivery getOrderDelivery() {
		return orderDelivery;
	}

	public void setOrderDelivery(CustomerOrdersDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}

	public CustomerPaymentMethods getPayment() {
		return payment;
	}

	public void setPayment(CustomerPaymentMethods payment) {
		this.payment = payment;
	}

	public RefOrderStatusCodes getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(RefOrderStatusCodes orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}


}
