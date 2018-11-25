package com.sanjayd.orderservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerOrderProducts implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EmbeddedOrderProductId id;

	private int quantity;
	
	private String comment;
	
	public EmbeddedOrderProductId getId() {
		return id;
	}

	public void setId(EmbeddedOrderProductId id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}
