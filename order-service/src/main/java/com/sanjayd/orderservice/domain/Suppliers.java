package com.sanjayd.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suppliers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@Column(unique=true,nullable = false)
	private String supplierCode;
	
	private String supplierName;
	
	private String otherSuppllierDetails;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getOtherSuppllierDetails() {
		return otherSuppllierDetails;
	}

	public void setOtherSuppllierDetails(String otherSuppllierDetails) {
		this.otherSuppllierDetails = otherSuppllierDetails;
	}

}
