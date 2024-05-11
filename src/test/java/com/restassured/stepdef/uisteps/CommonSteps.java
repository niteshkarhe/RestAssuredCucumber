package com.restassured.stepdef.uisteps;

import com.restassured.hook.Hooks;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps 
{

	@When("user launches browser with url")
	public void user_launches_browser_with_url() {
	    Hooks.getDriverManager().getDriver();
	}

	@Then("user logs into application")
	public void user_logs_into_application() {
	    
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
