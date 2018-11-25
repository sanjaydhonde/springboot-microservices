package com.sanjayd.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RefPaymentMethods implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	
	@Column(unique=true,nullable = false)
	private String paymentMethodCode;
	
	private String paymentMethodDescription;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}

	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}

	public String getPaymentMethodDescription() {
		return paymentMethodDescription;
	}

	public void setPaymentMethodDescription(String paymentMethodDescription) {
		this.paymentMethodDescription = paymentMethodDescription;
	}

}
