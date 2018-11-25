package com.sanjayd.productservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class RefDeliveryStatusCodes implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
	
	@Enumerated(EnumType.STRING)
	private DeliveryStatusCodes deliveryStatusCodes;
	
	private String deliveryStatusDescr;

	public DeliveryStatusCodes getDeliveryStatusCodes() {
		return deliveryStatusCodes;
	}

	public void setDeliveryStatusCodes(DeliveryStatusCodes deliveryStatusCodes) {
		this.deliveryStatusCodes = deliveryStatusCodes;
	}

	public String getDeliveryStatusDescr() {
		return deliveryStatusDescr;
	}

	public void setDeliveryStatusDescr(String deliveryStatusDescr) {
		this.deliveryStatusDescr = deliveryStatusDescr;
	}

}
