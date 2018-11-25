package com.sanjayd.productservice.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class Products implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

	@Column(nullable = false)
    private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="categoryName" ,referencedColumnName="name")	
	private ProductCategories category;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="verId" ,referencedColumnName="version")
	private ProductVersion ver;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productType" ,referencedColumnName="productTypeCode")
	private RefProductTypes productType;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="supplierCode" ,referencedColumnName="supplierCode")
	private Suppliers suppliers;
	
	@OneToMany( mappedBy="products", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<URLS> urls;
	
	@OneToMany( mappedBy="products", cascade=CascadeType.REMOVE, orphanRemoval=true)
	private List<TestTBD> test;
	
	private String description;
	
	private String url;
	
	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private Boolean isEnabled;
	
	private double cost;
	
	private String title; 
	
	private long rank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public ProductCategories getCategory() {
		return category;
	}

	public void setCategory(ProductCategories category) {
		this.category = category;
	}
	
	public ProductVersion getVer() {
		return ver;
	}

	public void setVer(ProductVersion ver) {
		this.ver = ver;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public List<URLS> getUrls() {
		return urls;
	}

	public void setUrls(List<URLS> urls) {
		this.urls = urls;
	}

	public List<TestTBD> getTest() {
		return test;
	}

	public void setTest(List<TestTBD> test) {
		this.test = test;
	}

}
