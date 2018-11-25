package com.sanjayd.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RefOrderStatusCodes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@Enumerated(EnumType.STRING)
	private OrderStatusCodes orderStatusCode;
	
	private String orderStatusDescr;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getOrderStatusDescr() {
		return orderStatusDescr;
	}

	public void setOrderStatusDescr(String orderStatusDescr) {
		this.orderStatusDescr = orderStatusDescr;
	}

	public OrderStatusCodes getOrderStatusCode() {
		return orderStatusCode;
	}

	public void setOrderStatusCode(OrderStatusCodes orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}


	
}
