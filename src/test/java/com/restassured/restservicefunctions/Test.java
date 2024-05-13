package com.restassured.restservicefunctions;

import static io.restassured.RestAssured.given;

import java.net.URI;
import java.net.URISyntaxException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Test {
	private static Response response;
	private static final String OAUTH_TOKEN = "b04f6dacef83cf2df7ad5ab9bdb060cd41ee18dd";
	
	public void requestOAuthPost() throws URISyntaxException
	{
		//RestAssured.baseURI = "http://coop.apps.symfonycasts.com/api/1047/eggs-collect";
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com/api/1047/chickens-feed";
		
		
		response =  given()
			       .log()
			       .all()
			       .auth()
			       .oauth2(OAUTH_TOKEN)
				   .post();
		
		System.out.println(response.getStatusCode());
		System.out.println(response.thenReturn().asString());
	}
	
	public void requestOAuthGet() throws URISyntaxException
	{
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com/api/me";
		//Do not have access to above end point
		response =  given()
			       .log()
			       .all()
			       .auth()
			       .oauth2(OAUTH_TOKEN)
				   .get();
		
		System.out.println(response.getStatusCode());
		System.out.println(response.thenReturn().asString());
	}
	
	
	
	public void test()
	{
		//RestAssured.baseURI = "http://localhost:8080/laptop-bag/webapi/api/add";
		
		Response response = given()
		.accept(ContentType.JSON)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body("{\r\n" + 
				" \"BrandName\": \"Sony\",\r\n" + 
				" \"Features\": {\r\n" + 
				"  \"Feature\": [\r\n" + 
				"   \"9GB RAM\",\r\n" + 
				"   \"8TB RAM\",\r\n" + 
				"   \"LED Display\"\r\n" + 
				"  ]\r\n" + 
				" },\r\n" + 
				" \"Id\": 201,\r\n" + 
				" \"LaptopName\": \"Go Pie\"\r\n" + 
				"}")
		.when()
		.post("http://localhost:8080/laptop-bag/webapi/api/add");
		
		System.out.println(response.thenReturn().asString());
	}
	
	public static void main(String[] args) throws URISyntaxException
	{
		//new Test().requestOAuthPost();
		new Test().test();
	}

}
