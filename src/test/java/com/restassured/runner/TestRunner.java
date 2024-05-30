package com.restassured.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/CvShortlisterFeatures", "src/test/resources/RequestAndUIFeatures"}, 
				glue = { "com.restassured.stepdef", "com.restassured.setup" }, // Path of step definition file 
				plugin = { "pretty", "html:target/cucumber-reports/cucumber-html-report",
				"json:target/cucmber-reports/CucumberTestReport.json", 
				"rerun:target/cucmber-reports/rerun.txt",
		// ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		// ,"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/cucumber-html-report/report.html"
		} // To generate types of reporting
		, tags = "@docstringtype and not @parametertype and not @datatabletype and not @SmokeUI and not @datatable and not @GetUser and not @Candidates and not @SaveUser"
		, monochrome = true // To print output on console in readable format
		, dryRun = false // When set true, it will check mapping between feature file and step definition
							// file and will not run any scenario actuall
)

public class TestRunner extends AbstractTestNGCucumberTests
{
	@DataProvider(parallel = false) // Default thread count is 10
	@Override
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
