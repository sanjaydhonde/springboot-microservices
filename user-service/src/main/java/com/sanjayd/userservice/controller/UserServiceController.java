package com.sanjayd.userservice.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanjayd.userservice.json.model.Response;
import com.sanjayd.userservice.gson.provider.GsonProvider;
import com.sanjayd.userservice.service.UserService;

@Controller
public class UserServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	
	@Autowired
	private GsonProvider gsonProvider;
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@RequestMapping(value = "/verifyEmail", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public final String verifyEmail(@RequestParam("tok") String token,
			@RequestParam("email") String email,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			return userService.verifyEmail(email,token);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Error");
			// response.setMessage(e.getMessage());
		}
		return gsonProvider.getGson().toJson(response);
	}

	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public final String authenticate(@RequestBody final String json,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			return userService.authenticate(json);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Error");
			// response.setMessage(e.getMessage());
		}
		return gsonProvider.getGson().toJson(response);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public final String registration(@RequestBody final String json,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			return userService.registration(json);
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setStatus("Error");
			// response.setMessage(e.getMessage());
		}
		return gsonProvider.getGson().toJson(response);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public final String addCustomer(@RequestBody final String json,
			@RequestHeader HttpHeaders headers) {
		Response response = new Response();
		try {
			userService.addCustomer(json);
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
