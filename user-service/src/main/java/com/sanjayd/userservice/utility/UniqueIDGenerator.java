package com.sanjayd.userservice.utility;

import java.util.UUID;

public class UniqueIDGenerator {
	
	public static String getUniqueID(){
		UUID uniqueKey = UUID.randomUUID();  
		return uniqueKey.toString();
	}

}
