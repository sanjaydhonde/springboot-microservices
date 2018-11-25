package com.sanjayd.productservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

public class AddProductVersionRequest {
	
	private long version;
	
	private String type="ADD_PRODUCT_VERSION";
	
	private String timestamp;
	
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimestamp() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp=timestamp;
	}

}
