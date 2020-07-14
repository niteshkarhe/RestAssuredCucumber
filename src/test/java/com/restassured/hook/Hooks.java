package com.restassured.hook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.restassured.browserDriverConfiguration.DriverManager;
import com.restassured.browserDriverConfiguration.DriverManagerFactory;
import com.restassured.fileReader.BrowserEnvironmentReader;
import com.restassured.fileReader.ExcelData;
import com.restassured.fileReader.RequestEnvironmentReader;
import com.restassured.utils.Utility;

import cucumber.api.PickleStepTestStep;
import cucumber.api.Scenario;
import cucumber.api.TestCase;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;

public class Hooks {
	private static Map<String, List<String>> browserEnvMap = new HashMap<String, List<String>>();
	private static Map<String, List<String>> requestEnvMap = new HashMap<String, List<String>>();
	private static DriverManager driver;
	
	public static Map<String, List<String>> getBrowserEnv()
	{
		return browserEnvMap;
	}
	
	public static DriverManager getDriverManager()
	{
		return driver;
	}
	
	public static Map<String, List<String>> getRequestEnv()
	{
		return requestEnvMap;
	}
	
	public static List<HashMap<String, String>> getExcelData()
	{	
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		String filePath = null;
		String sheetName = null;
		String testname = null;
		if(getRequestEnv().get("TestDataFilePath").get(0)!="")
		{
			filePath = System.getProperty("user.dir")+getRequestEnv().get("TestDataFilePath").get(0);
			sheetName = getRequestEnv().get("SheetName").get(0);
			testname = getRequestEnv().get("Testcase").get(0);
			dataList = new ExcelData(filePath, sheetName, testname).getData();
		}
		else
		{
			return null;
		}
		return dataList;
	}
	
	@Before("@EnvironmentSetup")
	public void BrowserEnvironmentSetup(Scenario scenario) {
		browserEnvMap = new BrowserEnvironmentReader().getBrowserEnvironment();
		driver = DriverManagerFactory.getManager(browserEnvMap.get("BrowserName").get(0));
		//driver.getDriver();
		driver.getService();
		System.out.println(browserEnvMap);
	}
	
	/*@BeforeStep
	public void BeforeStep(Scenario scenario)
	{
		List<PickleStepTestStep> steps = getSteps(scenario);
		String requestName = null;
		for(PickleStepTestStep ts : steps)
		{
			if(Utility.ifMatchPattern("request.", ts.getStepText()))
			{
				String requestString = Utility.getSubtextBetween(ts.getStepText(), "\"", "\"");
				requestName = Utility.getSubtextAfter(requestString, "request.");
			}
		}
		requestEnvMap = new RequestEnvironmentReader(requestName).getRequestEnvironment();
		requestEnvMap.put("Testcase", Utility.getListData(scenario.getName()));
		System.out.println(requestEnvMap);
	}*/

	@Before
	public void BeforeScenario(Scenario scenario) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		List<PickleStepTestStep> steps = getSteps(scenario);
		String requestName = null;
		for(PickleStepTestStep ts : steps)
		{
			if(Utility.ifMatchPattern("request.", ts.getStepText()))
			{
				String requestString = Utility.getSubtextBetween(ts.getStepText(), "\"", "\"");
				requestName = Utility.getSubtextAfter(requestString, "request.");
			}
		}
		requestEnvMap = new RequestEnvironmentReader(requestName).getRequestEnvironment();
		requestEnvMap.put("Testcase", Utility.getListData(scenario.getName()));
		System.out.println(requestEnvMap);
	}
	
	private List<PickleStepTestStep> getSteps(Scenario scenario)
	{
		List<String> tags = (ArrayList<String>) scenario.getSourceTagNames();
		System.out.println("At Hooks: " + scenario.getId());
		Iterator ite = tags.iterator();
		List<PickleStepTestStep> testSteps = null;
		try
		{
			while (ite.hasNext()) { 

				String buffer = ite.next().toString();
				if (buffer.startsWith("")) {

					Field f = scenario.getClass().getDeclaredField("testCase");
					f.setAccessible(true);
					TestCase r = (TestCase) f.get(scenario);

					testSteps = r.getTestSteps().stream()
							.filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x)
							.collect(Collectors.toList());
					/*for (PickleStepTestStep ts : testSteps) {

						System.out.println(ts.getStepText());// will print your test case steps

					}*/
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return testSteps;
	}
	
	@After("@Teardown")
	public void SuiteTeardown()
	{
		driver.quitDriver();
	}
	
	@After
	public void ScenarioTeardown(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			
		}
		driver.closeDriver();
	}
	 
}
