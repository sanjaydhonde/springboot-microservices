package com.sanjayd.userservice.gson.provider;

import org.springframework.stereotype.Component;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

@Component
public class GsonProvider {
	
	private Gson gson;
	private JsonParser parser;
	
	//First way to create a Gson object for faster coding
	public Gson getGson(){
		gson = new Gson();
		return gson;
		
	}
	

	 
	//Second way to create a Gson object using GsonBuilder
	public Gson getGsonBuilder(){ 
		gson = new GsonBuilder()
	             .disableHtmlEscaping()
	             .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
	             .setPrettyPrinting()
	             .serializeNulls()
	             .create();
		return gson;
	}
	
	public JsonParser getJsonParser(){
		parser = new JsonParser();
		return parser;
		
	}

}
