package com.restassured.restservicefunctions;

import java.util.HashMap;

public class RequestDataFunctions 
{
	private static HashMap<String, String> requestBody = new HashMap<String, String>();
	
	public static void setRequestData(HashMap<String, String> reqData)
	{
		requestBody = reqData;
	}
	
	public static HashMap<String, String> getRequestData()
	{
		return requestBody;
	}
}
