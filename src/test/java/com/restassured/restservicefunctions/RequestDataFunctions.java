package com.restassured.restservicefunctions;

public class RequestDataFunctions 
{
	private static String requestBody;
	
	public static void setRequestData(String reqData)
	{
		requestBody = reqData;
	}
	
	public static String getRequestData()
	{
		return requestBody;
	}
}
