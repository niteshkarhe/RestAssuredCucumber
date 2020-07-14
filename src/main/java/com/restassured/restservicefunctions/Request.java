package com.restassured.restservicefunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restassured.hook.Hooks;

import io.restassured.http.ContentType;

public class Request 
{
	//Map<String, List<String>> browserEnv = new HashMap<String, List<String>>();
	private static Map<String, List<String>> requestEnv = new HashMap<String, List<String>>();
	private static HashMap<String, String> requestData = new HashMap<String, String>();
	
	public Request(String requestName)
	{
		if(Hooks.getRequestEnv().get("requestName").get(0).equals(requestName))
		{
			requestEnv = Hooks.getRequestEnv();
			requestData = RequestDataFunctions.getRequestData();
		}
		else
		{
			System.err.println("requestconfiguration.xml: the provided requestname from feature file does not exists. Provided request from BDD ["+requestName+"] and request name exists in request config map ["+Hooks.getRequestEnv().get("requestName").get(0)+"]");
		}
	}
	
	protected static String getURL()
	{
		String URL = requestEnv.get("baseUrl").get(0)+requestEnv.get("endpoint").get(0);
		//String URL = requestData.get("Get URL");
		return URL;
	}
	
	protected static HashMap<String, String> getRequestData()
	{
		return requestData;
	}
	
	protected static String getMethod()
	{
		return requestEnv.get("method").get(0);
	}
	
	protected static List<String> getHeaders()
	{
		return requestEnv.get("headers");
	}
	
	protected static List<String> getParams()
	{
		return requestEnv.get("params");
	}
	
	protected static ContentType getBodyContentType()
	{
		String bodyContentType = requestEnv.get("bodyContentType").get(0);
		switch(bodyContentType)
		{
			case "ContentType.TEXT": return ContentType.TEXT;
			case "ContentType.JSON": return ContentType.JSON;
			case "ContentType.XML": return ContentType.XML;
		}
		return null;
	}
	
	protected static ContentType getContentType()
	{
		String contentType = requestEnv.get("contentType").get(0);
		switch(contentType)
		{
			case "ContentType.TEXT": return ContentType.TEXT;
			case "ContentType.JSON": return ContentType.JSON;
			case "ContentType.XML": return ContentType.XML;
		}
		return null;
	}
	
	protected static String getBasicAuthUname()
	{
		return requestEnv.get("basicAuthUname").get(0);
	}
	
	protected static String getBasicAuthPwd()
	{
		return requestEnv.get("basicAuthPwd").get(0);
	}
}