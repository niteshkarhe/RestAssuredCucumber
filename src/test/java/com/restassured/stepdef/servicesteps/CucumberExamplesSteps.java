package com.restassured.stepdef.servicesteps;

import java.util.List;

import com.restassured.setup.ExtentReport;

import io.cucumber.java.en.Given;

public class CucumberExamplesSteps extends ExtentReport
{
	@Given("the following animals:")
	public void the_following_animals(io.cucumber.datatable.DataTable dataTable)
	{
		List<String> animals = dataTable.asList();
		for (String animal : animals)
		{
			LogResult(false, "Animal: [<b>" + animal + "</b>]");
		}
	}
}
