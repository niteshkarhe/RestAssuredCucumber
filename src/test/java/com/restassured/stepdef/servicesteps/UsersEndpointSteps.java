package com.restassured.stepdef.servicesteps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.restassured.fileReader.PropertyReader;
import com.restassured.pojoutilities.ConvertRespJsonToPOJO;
import com.restassured.responsepojo.getuser.GetUser;
import com.restassured.responsepojo.getuser.GetUserResponseHandler;
import com.restassured.restservicefunctions.RequestBuilder;
import com.restassured.restservicefunctions.RequestDataFunctions;
import com.restassured.setup.ExtentReport;
import com.restassured.setup.Hooks;
import com.restassured.utils.Utility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UsersEndpointSteps extends ExtentReport
{
	private List<Object> testdata = null;
	private RequestBuilder reqInvoker = null;
	private Map<String, List<String>> metaData = new HashMap<String, List<String>>();
	private String responsePkgName = Hooks.projectConfigs.getOutputRespPojoPkg();
	private String responseDirName = Hooks.projectConfigs.getOutputRespPojoDir();
	
	@Given("user endpoint is run to store candidate questioneer data {string}")
	public void user_endpoint_is_run_to_store_candidate_questioneer_data(String string)
	{        
		testdata = Hooks.getJsonData();
		
		if(testdata != null)
		{
			LogResult(false, "Request paylad: " + testdata.get(Integer.parseInt(string)).toString());
			RequestDataFunctions.setRequestData(testdata.get(Integer.parseInt(string)).toString());
			metaData.put("baseUrl", new ArrayList<String>() { { add("http://127.0.0.1:8080/api/"); } });
			metaData.put("endpoint", new ArrayList<String>() { { add("user"); } });
			metaData.put("method", new ArrayList<String>() { { add("POST"); } });
			metaData.put("headers", null);
			metaData.put("params", null);
			metaData.put("bodyContentType", new ArrayList<String>() { { add("ContentType.JSON"); } });
			metaData.put("contentType", new ArrayList<String>() { { add("ContentType.JSON"); } });
		}
		
		reqInvoker = new RequestBuilder(metaData);
		reqInvoker.request();
	}
	
	@Then("verify that the response is received successfully")
	public void verify_that_the_response_is_received_successfully() {
		
		System.out.println(RequestBuilder.getStatusCode());
		if (RequestBuilder.getStatusCode().toString().equals("200"))
		{
			LogResult(false, "Response payload: " + RequestBuilder.getResponse());
		}
		else
		{
			LogResult(true, "Response payload: " + RequestBuilder.getResponse());
		}
	}
	
	@Given("user endpoint is run to get the details of all user question responses")
	public void user_endpoint_is_run_to_get_the_details_of_all_user_question_responses()
	{
		metaData.put("baseUrl", new ArrayList<String>() { { add("http://127.0.0.1:8080/api/"); } });
		metaData.put("endpoint", new ArrayList<String>() { { add("user"); } });
		metaData.put("method", new ArrayList<String>() { { add("GET"); } });
		metaData.put("headers", null);
		metaData.put("params", null);
		metaData.put("contentType", new ArrayList<String>() { { add("ContentType.JSON"); } });
		
		reqInvoker = new RequestBuilder(metaData);
		reqInvoker.request();
	}
	
	@Then("verify that the received user details are correct")
	public void verify_that_the_received_user_details_are_correct()
	{
		File outputRespPojoDir=new File(responseDirName);
		String response = RequestBuilder.getResponse();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(response);
		response = gson.toJson(je);
		LogResult(false, "Respose received: " + response);
		try {
			ConvertRespJsonToPOJO.convertResponseJSON(response, outputRespPojoDir, responsePkgName + ".getuser", "GetUser");
			List<GetUser> userObj = new GetUserResponseHandler().getGetUserObject(response);
			
			for (GetUser obj : userObj)
			{
				if (obj.getName().equals("Padma Guduru") && obj.getQuestion().equals("What is your name?"))
				{
					if (obj.getAnswer().equals("Padma Guduru"))
					{
						LogResult(false, "Padma Guguru response for question - What is your name? is received successfully as [<b>" + obj.getAnswer() + "</b>");
					}
					else
					{
						LogResult(true, "Padma Guguru response for question - What is your name? is not correct. Actual: [" + obj.getAnswer() + "]");
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}