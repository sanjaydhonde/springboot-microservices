package com.sanjayd.orderservice.json.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetBannersResponse {

	private List<BannerJsonModel> banners ;
	private String type="GET_BANNERS";
	private String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public List<BannerJsonModel> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerJsonModel> banners) {
		this.banners = banners;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTimestamp() {
		//timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp=timestamp;
	}
   
}
