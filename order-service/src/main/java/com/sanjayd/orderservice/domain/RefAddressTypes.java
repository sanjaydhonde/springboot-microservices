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
public class RefAddressTypes implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@Enumerated(EnumType.STRING)
	private AddressTypeCode addressTypeCode;
	
	private String addressTypeDescr;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public AddressTypeCode getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(AddressTypeCode addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public String getAddressTypeDescr() {
		return addressTypeDescr;
	}

	public void setAddressTypeDescr(String addressTypeDescr) {
		this.addressTypeDescr = addressTypeDescr;
	}

	
}
