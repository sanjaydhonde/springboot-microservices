package com.sanjayd.userservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrdersDelivery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private long Id;
	
	@OneToOne
	private CustomerOrders orders;
	
	@ManyToOne 
	@JoinColumn(name="deliveryStatus" ,referencedColumnName="deliveryStatusCodes")
	private RefDeliveryStatusCodes statusCodes;
	
	private Date dateReported;

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

	public RefDeliveryStatusCodes getStatusCodes() {
		return statusCodes;
	}

	public void setStatusCodes(RefDeliveryStatusCodes statusCodes) {
		this.statusCodes = statusCodes;
	}

	public Date getDateReported() {
		return dateReported;
	}

	public void setDateReported(Date dateReported) {
		this.dateReported = dateReported;
	}
	
}
