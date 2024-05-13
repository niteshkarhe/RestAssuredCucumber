package com.restassured.stepdef.servicesteps;

import com.restassured.restservicefunctions.RequestBuilder;
import com.restassured.restservicefunctions.RequestDataFunctions;
import com.restassured.setup.Hooks;
import com.restassured.utils.Utility;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.List;
import org.testng.Assert;

public class CommonSteps 
{
	public List<HashMap<String,String>> testdata;
	
	@Then("get the response body")
	public void get_the_response_body() {
		System.out.println(RequestBuilder.getResponse());
	}

	@Then("^response should have status code '(\\d+)'$")
	public void response_should_have_status(int code) {
	   Assert.assertEquals(RequestBuilder.getStatusCode(), code);
	}

	@Then("^response should have status \"([^\"]*)\"$")
	public void verify_response_has(String status){
	    Assert.assertTrue(RequestBuilder.getReasonPhrase().contains(status), "Status is not OK");
	}
	
	@Then("verify the brandname of requested id")
	public void verify_the_brandname_of_requested_id() {
		RequestBuilder.assertRespBodyTagValue("BrandName", "BrandName");
	}
	
	@Then("verify {string} at jsonpath {string}")
	public void verify_at_jsonpath(String value, String jsonpath) {
		RequestBuilder.verifyValueAtJsonPath(value, jsonpath);
	}
	
	@Then("verify {string} at xmlpath {string}")
	public void verify_at_xmlpath(String value, String xmlpath) {
		RequestBuilder.verifyValueAtXpathPath(value, xmlpath);
	}
}