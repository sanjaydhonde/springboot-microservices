package com.sanjayd.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class EmbeddedCustAddrId implements Serializable{
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private Customers cust;
	
	@ManyToOne
	private Address addr;

	public Customers getCust() {
		return cust;
	}

	public void setCust(Customers cust) {
		this.cust = cust;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}
}
