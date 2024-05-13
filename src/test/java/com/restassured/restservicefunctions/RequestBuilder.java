package com.restassured.restservicefunctions;

import static io.restassured.RestAssured.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import com.restassured.utils.Utility;
import static org.hamcrest.Matchers.*;

public class RequestBuilder extends Request
{
	private static Response response;
	
	public RequestBuilder(Map<String, List<String>> metaData)
	{
		super(metaData);
	}
	
	private void requestGet()
	{
		try 
		{
			if(getHeaders() != null)
			{
				response = given()
						.log()
						.all()
						.headers(resolveHeaders(getHeaders()))
						.accept(getContentType()) //
						.when()
						.get(new URI(getURL()));
			}
			if(getParams() != null)
			{
				response = given()
						.accept(getContentType())
						.params(resolveParams(getParams()))
						.when()
						.get(new URI(getURL()));
			}
			else 
			{
				response = given()
						.when()
						.accept(getContentType())
						.get(new URI(getURL()));
			}
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void requestGetAuth()
	{
		try 
		{
			if(getParams() != null)
			{
				response = given()
						.accept(getContentType())
						.params(resolveParams(getParams()))
						.when()
						.get(new URI(getURL()));
			}
			else 
			{
				response = given()
						.auth()
						.basic(getBasicAuthUname(), getBasicAuthPwd())
						.when()
						.accept(getContentType())
						.get(new URI(getURL()));
			}
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void requestPost()
	{
		try
		{
			if(getHeaders() != null)
			{
				response = given()
						.headers(resolveHeaders(getHeaders()))
						.accept(getContentType())
						.and()
					    .body(getRequestPayload())
					    .when()
					    .post(new URI(getURL()));
			}
			else if(getParams() != null)
			{
				System.out.println("getParams().get(0) "+getParams().get(0));
				response = given()
						.accept(getContentType()) //This is resp content type
						.params(resolveParams(getParams()))
						.with()
					    .contentType(getBodyContentType()) //This is body content type
					    .and()
					    .body(getRequestPayload())
					    .when()
					    .post(new URI(getURL()));
				
				
			}
			else
			{	
				System.out.println("URL: " + getURL());
			response =  given()
					   .accept(getContentType())
					   .with()
					   .contentType(getBodyContentType())
					   .and()
					   .body(getRequestPayload())
					   .when()
					   .post(getURL());
			}
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void requestPut()
	{
		try
		{
			if(getHeaders() != null)
			{
				response = given()
						.headers(resolveHeaders(getHeaders()))
						.accept(getContentType())
						.and()
					    .body(getRequestPayload())
					    .when()
					    .put(new URI(getURL()));
			}
			else if(getParams() != null)
			{
				response = given()
						.accept(getContentType())
						.params(resolveParams(getParams()))
						.with()
					    .contentType(getBodyContentType())
					    .and()
					    .body(getRequestPayload())
					    .when()
					    .put(new URI(getURL()));
			}
			else
			{	
			response =  given()
					   .accept(getContentType())
					   .with()
					   .contentType(getBodyContentType())
					   .and()
					   .body(getRequestPayload())
					   .when()
					   .put(getURL());
			}
		} 
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void requestDelete()
	{
		response =  expect()
					.statusCode(HttpStatus.SC_OK)
					.when()
					.delete(getURL());
	}
	
	private static Map<String, String> resolveHeaders(List<String> headers)
	{
		Map<String, String> map = new HashMap<String, String>();
		for(String header : headers)
		{
			String key = Utility.getSubtextBefore(header, ":").replaceAll("\"", "");
			String value = Utility.getSubtextAfter(header, ":").replaceAll("\"", "");
			map.put(key, value);
		}
		return map;
	}
	
	private static Map<String, String> resolveParams(List<String> params)
	{
		Map<String, String> map = new HashMap<String, String>();
		for(String header : params)
		{
			String key = Utility.getSubtextBefore(header, ",").replaceAll("\"", "");
			String value = Utility.getSubtextAfter(header, ",").replaceAll("\"", "");
			map.put(key, value);
		}
		return map;
	}
	
	public static String getResponse()
	{
		return response.asString();
	}
	
	public static Object getStatusCode()
	{
		return new Integer(response.thenReturn().statusCode());
	}
	
	public static String getReasonPhrase()
	{
		return response.thenReturn().getStatusLine();
	}
	
	public static void assertStatusOK()
	{
		response
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);
	}
	
	public static void assertRespBodyTagValue(String tag, String value)
	{
		response
		.then()
		.body(tag, equalToIgnoringCase(getRequestPayload()));
	}
	
	public static void verifyValueAtJsonPath(String value, String jsonpath)
	{
		JsonPath jsonPathEvaluator = response.jsonPath();
		if(jsonPathEvaluator.get(jsonpath) instanceof ArrayList)
		{
			List<String> list = new ArrayList<String>();
			list.addAll(jsonPathEvaluator.get(jsonpath));
			Assert.assertTrue(list.contains(getRequestPayload()), "["+getRequestPayload()+"] is not present at jsonpath ["+jsonpath+"]");
		}
		else if(jsonPathEvaluator.get(jsonpath) instanceof String)
		{
			String actual = jsonPathEvaluator.get(jsonpath).toString();
			Assert.assertTrue(actual.contains(getRequestPayload()), "["+getRequestPayload()+"] is not present at jsonpath ["+jsonpath+"]");
		}
	}
	
	public static void verifyValueAtXpathPath(String value, String xmlpath)
	{
		XmlPath jsonPathEvaluator = response.xmlPath();
		if(jsonPathEvaluator.get(xmlpath) instanceof ArrayList)
		{
			List<String> list = new ArrayList<String>();
			list.addAll(jsonPathEvaluator.get(xmlpath));
			Assert.assertTrue(list.contains(getRequestPayload()), "["+getRequestPayload()+"] is not present at jsonpath ["+xmlpath+"]");
		}
		else if(jsonPathEvaluator.get(xmlpath) instanceof String)
		{
			String actual = jsonPathEvaluator.get(xmlpath).toString();
			System.out.println(actual);
			Assert.assertTrue(actual.contains(getRequestPayload()), "["+getRequestPayload()+"] is not present at jsonpath ["+xmlpath+"]");
		}
	}
	
	public void request()
	{
		switch(getMethod())
		{
			case "GET": requestGet(); break;
			case "POST": requestPost(); break;
			case "PUT": requestPut(); break;
			case "DELETE": requestDelete(); break;
			case "GET_AUTH": requestGetAuth(); break;
			default: System.err.println("No request method present"); break;
		}
	}
	
	public static void main(String args[])
	{
		/*
		 * RequestBuilder req = new RequestBuilder("postWithBodyContent");
		 * req.requestPost();
		 */
	}
}