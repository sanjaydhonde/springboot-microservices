package com.sanjayd.userservice.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RefProductTypes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@Column(unique=true,nullable = false)
	private String productTypeCode;
	
	@OneToMany(mappedBy="parentProductTypeCode")
	private List<RefProductTypes> productTypeCodeList;
	
	@ManyToOne
	private RefProductTypes parentProductTypeCode;

	private int levelNumber;
	
	private String productTypeDescription;
	
	public List<RefProductTypes> getProductTypeCodeList() {
		return productTypeCodeList;
	}
	public void setProductTypeCodeList(List<RefProductTypes> productTypeCodeList) {
		this.productTypeCodeList = productTypeCodeList;
	}
	
	public RefProductTypes getParentProductTypeCode() {
		return parentProductTypeCode;
	}
	public void setParentProductTypeCode(RefProductTypes parentProductTypeCode) {
		this.parentProductTypeCode = parentProductTypeCode;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	public String getProductTypeDescription() {
		return productTypeDescription;
	}
	public void setProductTypeDescription(String productTypeDescription) {
		this.productTypeDescription = productTypeDescription;
	}
	public String getProductTypeCode() {
		return productTypeCode;
	}
	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
}
