package com.restassured.restservicefunctions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restassured.setup.Hooks;

import io.restassured.http.ContentType;

public abstract class Request 
{
	//Map<String, List<String>> browserEnv = new HashMap<String, List<String>>();
	private static Map<String, List<String>> requestEnv = new HashMap<String, List<String>>();
	private static String requestPayload;
	
	public Request(Map<String, List<String>> metaData)
	{
		requestEnv = metaData;
		requestPayload = RequestDataFunctions.getRequestData();
	}
	
	public abstract void request();
	
	protected static String getURL()
	{
		String baseUrl = requestEnv.get("baseUrl").get(0).endsWith("/") ? requestEnv.get("baseUrl").get(0) : requestEnv.get("baseUrl").get(0) + "/";
		String URL = baseUrl + requestEnv.get("endpoint").get(0);
		return URL;
	}
	
	protected static String getRequestPayload()
	{
		return requestPayload;
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