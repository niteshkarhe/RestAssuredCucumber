package com.restassured.stepdef.servicesteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restassured.restservicefunctions.RequestBuilder;
import com.restassured.restservicefunctions.RequestDataFunctions;
import com.restassured.setup.ExtentReport;
import com.restassured.setup.Hooks;

import io.cucumber.java.en.Given;

public class CandidateEndpointSteps extends ExtentReport
{
	List<Object> testdata = null;
	Map<String, List<String>> metaData = new HashMap<String, List<String>>();
	
	@Given("candidate endpoint is run to create candidate {string}")
	public void candidate_endpoint_is_run_to_create_candidate(String string) 
	{
		testdata = Hooks.getJsonData();
		
		if(testdata != null)
		{
			RequestDataFunctions.setRequestData(testdata.get(Integer.parseInt(string)).toString());
			LogResult(false, "Request paylad: " + testdata.get(Integer.parseInt(string)).toString());
			metaData.put("baseUrl", new ArrayList<String>() { { add("http://127.0.0.1:8080/api/"); } });
			metaData.put("endpoint", new ArrayList<String>() { { add("candidates"); } });
			metaData.put("method", new ArrayList<String>() { { add("POST"); } });
			metaData.put("headers", null);
			metaData.put("params", null);
			metaData.put("bodyContentType", new ArrayList<String>() { { add("ContentType.JSON"); } });
			metaData.put("contentType", new ArrayList<String>() { { add("ContentType.JSON"); } });
		}
		
		RequestBuilder reqInvoker = new RequestBuilder(metaData);
		reqInvoker.request();
		System.out.println(RequestBuilder.getResponse());
	}
}
