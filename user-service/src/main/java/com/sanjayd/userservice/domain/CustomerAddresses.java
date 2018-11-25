package com.sanjayd.userservice.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity 
public class CustomerAddresses implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmbeddedCustAddrId id;

    private Date dateFrom;
    
	private Date dateTo;
	
	@ManyToOne 
	@JoinColumn(name="addressType" ,referencedColumnName="addressTypeCode")
	private RefAddressTypes addressType;

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public RefAddressTypes getAddressType() {
		return addressType;
	}

	public void setAddressType(RefAddressTypes addressType) {
		this.addressType = addressType;
	}

	public EmbeddedCustAddrId getId() {
		return id;
	}

	public void setId(EmbeddedCustAddrId id) {
		this.id = id;
	}


}


