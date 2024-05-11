package com.restassured.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.Pickle;
import io.cucumber.testng.TestNGCucumberRunner;



@CucumberOptions(
		features = "src/test/resources/RequestAndUIFeatures",
		glue = {"com.restassured.stepdef", "com.restassured.hook"} //Path of step definition file
		,plugin= {"pretty", "html:target/cucumber-reports/cucumber-html-report", 
				"json:target/cucmber-reports/CucumberTestReport.json",
				"rerun:target/cucmber-reports/rerun.txt"
				,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
				//,"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/cucumber-html-report/report.html"
				} //To generate types of reporting
		,tags= "@UI and not @Smoke and not @SmokeGet and not @SmokePost and not @SmokePut and not @SmokeDelete and not @SmokeAuth and not @Regression and not @ListDatatable"
		,monochrome=true //To print output on console in readable format
		,dryRun=false //When set true, it will check mapping between feature file and step definition file and will not run any scenario actuall		
		)

public class TestRunner
{
private TestNGCucumberRunner testNgCucumberRunner;
	
	@BeforeClass(alwaysRun=true)
	public void setUpClass() {
		testNgCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    /*@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
    	testNgCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());	
    }*/
	
	@Test(groups = "cucumber scenarios", description = "Runs Cucumber Feature", dataProvider = "scenario")
    public void feature(Pickle pickleEvent) throws Throwable {
    	testNgCucumberRunner.runScenario(pickleEvent);
    }
 
    @DataProvider(parallel=false) //Default thread count is 10
    public Object[][] scenario() {
        return testNgCucumberRunner.provideScenarios();
    }
 
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
    	testNgCucumberRunner.finish();
    }
}
