package com.sanjayd.userservice.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
	
	@Column(nullable = false,unique=true)
    private String email;
	
	@Column(nullable = false)
    private String saltedHash;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSaltedHash() {
		return saltedHash;
	}

	public void setSaltedHash(String saltedHash) {
		this.saltedHash = saltedHash;
	}


}
