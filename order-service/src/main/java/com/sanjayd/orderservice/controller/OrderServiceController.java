package com.sanjayd.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanjayd.orderservice.service.OrderService;
import com.sanjayd.orderservice.json.model.Response;
import com.sanjayd.orderservice.gson.provider.GsonProvider;

@Controller
public class OrderServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceController.class);
	
	@Autowired
	private GsonProvider gsonProvider;
	
	@Autowired
	OrderService orderService;
	
	@CrossOrigin
	@RequestMapping(value = "/getAllOrders", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public final String getAllOrders(@RequestBody final String json,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			return orderService.getAllOrders(json);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Error");
			// response.setMessage(e.getMessage());
		}
		return gsonProvider.getGson().toJson(response);
	}

	@CrossOrigin
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public final String addOrder(@RequestBody final String json,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			orderService.addOrder(json);
			response.setStatus("Success");
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Error");
			// response.setMessage(e.getMessage());
		}
		return gsonProvider.getGson().toJson(response);
	}
	
	public void setGsonProvider(GsonProvider gsonPro) {
		gsonProvider = gsonPro;
	}

		
}
