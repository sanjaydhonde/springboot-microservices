package com.sanjayd.orderservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Entity
public class CustomerPaymentMethods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@ManyToOne 
	private Customers customer;
	
	@OneToOne
	private CustomerOrders orders;
	
	@ManyToOne 
	private RefPaymentMethods paymentMethods;
	
	private String cardNumber;
	
	private Date dateFrom;
	
	private Date dateTo;
	
	private String otherDetails;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
	
	public CustomerOrders getOrders() {
		return orders;
	}

	public void setOrders(CustomerOrders orders) {
		this.orders = orders;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public RefPaymentMethods getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(RefPaymentMethods paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	

}
