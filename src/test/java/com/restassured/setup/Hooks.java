package com.restassured.setup;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.restassured.browserDriverConfiguration.DriverManager;
import com.restassured.browserDriverConfiguration.DriverManagerFactory;
import com.restassured.extentreports.ExtentManager;
import com.restassured.fileReader.ExcelData;
import com.restassured.utils.Utility;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import lombok.Getter;
import lombok.Setter;

public class Hooks {
	static {
		setSuiteReport(ExtentManager.createExtentReports());
	}

	private DriverManager factory = null;

	public static ProjectConfigs projectConfigs = InitializeConfig();

	@Getter
	@Setter
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	@Getter
	@Setter
	public static ExtentReports suiteReport;

	@Getter
	@Setter
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	@Getter
	@Setter
	public static ThreadLocal<Boolean> hasFailures = new ThreadLocal<Boolean>();

	@Getter
	@Setter
	public static List<String> TestNames = new ArrayList<String>();

	public static List<HashMap<String, String>> getExcelData() {
		List<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
		String filePath = null;
		String sheetName = null;
		String testname = null;
		if (!projectConfigs.getEnvironment().equals("")) {
			filePath = System.getProperty("user.dir") + "\\testdata\\servicedata\\" + projectConfigs.getEnvironment()
					+ "_" + projectConfigs.getFeatureFolderName().split("_")[1] + ".xlsx";

			sheetName = projectConfigs.getFeatureName();
			testname = projectConfigs.getScenarioName();
			dataList = new ExcelData(filePath, sheetName, testname).getData();
			System.out.println(dataList);
		} else {
			return null;
		}
		return dataList;
	}

	public static List<Object> getJsonData() {
		JSONParser jsonParser = new JSONParser();
		List<Object> dataList = new ArrayList<Object>();
		String filePath = null;
		if (!projectConfigs.getEnvironment().equals("")) {
			filePath = System.getProperty("user.dir") + "\\ServiceFiles\\RequestFiles\\" + projectConfigs.getEnvironment()
					+ "_" + projectConfigs.getFeatureName().split("_")[1] + ".json";
		} else {
			return null;
		}
		
        try (FileReader reader = new FileReader(filePath))
        {
        	JSONObject obj = (JSONObject) jsonParser.parse(reader);
        	String scenarioName = projectConfigs.getScenarioName();
        	JSONArray requestArray = (JSONArray) obj.get(scenarioName);
        	dataList.addAll(Arrays.asList(requestArray.toArray()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		return dataList;
	}
	
	@Before(order=0)
	public void ScenarioSetup(Scenario scenario) {
		System.out.println("#### Before");
		hasFailures.set(false);
		try
		{
			String featureName = FilenameUtils.getBaseName(scenario.getUri().toString());
			String featureFilePath = FilenameUtils.getPath(scenario.getUri().toString());
			String folderName = featureFilePath.split("/")[featureFilePath.split("/").length - 1];
			projectConfigs.setFeatureFolderName(folderName);
			projectConfigs.setFeatureName(featureName);
			projectConfigs.setScenarioName(scenario.getName());
			testReport.set(suiteReport.createTest(projectConfigs.getScenarioName()));
			setTestNames(new ArrayList<String>() { { add(projectConfigs.getScenarioName()); } });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Before(order=1, value="@UIOneTimeSetup")
	public void uIOneTimeSetUp(Scenario scenario)
	{
		if (projectConfigs.getBrowserScope().equals("class")) 
		{
			startWebDriver();
		}
		
		/*
		List<PickleStepTestStep> steps = getSteps(scenario);
		String requestName = null;
		for (PickleStepTestStep ts : steps) {
			if (Utility.ifMatchPattern("request.", ts.getStepText())) {
				String requestString = Utility.getSubtextBetween(ts.getStepText(), "\"", "\"");
				requestName = Utility.getSubtextAfter(requestString, "request.");
			}
		}
		*/
		
		/*
		 * requestEnvMap = new
		 * RequestEnvironmentReader(requestName).getRequestEnvironment();
		 * requestEnvMap.put("Testcase", Utility.getListData(scenario.getName()));
		 * System.out.println(requestEnvMap);
		 */
	}
	
	@Before(order=2, value="@UISetup")
	public void uISetUp(Scenario scenario)
	{
		System.out.println("#### Before Conditional");
		if (projectConfigs.getBrowserScope().equals("method")) 
		{
			startWebDriver();
		}
	}
	
	@AfterAll
	public static void uIOneTimeTeardown() {
		if (driver != null && projectConfigs.getBrowserScope().equals("class")) 
		{
			driver.get().quit();
		}
	}

	@After(order=1, value="@UITeardown")
	public void uITeardown(Scenario scenario) {
		System.out.println("#### After Conditional");
		if (scenario.isFailed()) {

		}
		if (projectConfigs.getBrowserScope().equals("method")) 
		{
			driver.get().quit();
		}
	}

	@After(order=0)
	public void TestMethodTearDown(Scenario scenario)
	{
		System.out.println("#### After");
		try
		{
			getTestNames().remove(scenario.getName());
			String testStatus = scenario.getStatus().name();
			
			Map<String, Status> logStatus = new HashMap<String, Status>();
			logStatus.put(scenario.getStatus().FAILED.name(), Status.FAIL);
			logStatus.put(scenario.getStatus().PASSED.name(), Status.PASS);
			logStatus.put(scenario.getStatus().SKIPPED.name(), Status.SKIP);
			
			testReport.get().log(logStatus.get(testStatus), "Test Complete. Result: " + logStatus.get(testStatus));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@After("@SuiteTearDown")
	public void tearDown()
	{
		getSuiteReport().flush();
	}
	
	private List<PickleStepTestStep> getSteps(Scenario scenario) {
		List<String> tags = (ArrayList<String>) scenario.getSourceTagNames();
		System.out.println("At Hooks: " + scenario.getId());
		Iterator ite = tags.iterator();
		List<PickleStepTestStep> testSteps = null;
		try {
			while (ite.hasNext()) {

				String buffer = ite.next().toString();
				if (buffer.startsWith("")) {
					Field f = scenario.getClass().getDeclaredField("delegate");
					f.setAccessible(true);
					TestCaseState tcs = (TestCaseState) f.get(scenario);

					Field f2 = tcs.getClass().getDeclaredField("testCase");
					f2.setAccessible(true);
					TestCase r = (TestCase) f2.get(tcs);

					testSteps = r.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep)
							.map(x -> (PickleStepTestStep) x).collect(Collectors.toList());
					/*
					 * for (PickleStepTestStep ts : testSteps) {
					 * 
					 * System.out.println(ts.getStepText());// will print your test case steps
					 * 
					 * }
					 */
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testSteps;
	}
	
	private void startWebDriver()
	{
		factory = DriverManagerFactory.getManager(projectConfigs.getBrowserName());
		driver.set(factory.getDriver());
		setDriver(driver);
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(projectConfigs.getImplicitWait()));
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(projectConfigs.getPageLoadWait()));
		// driver.get().manage().deleteAllCookies();
		driver.get().manage().window().maximize();
		driver.get().navigate().to(projectConfigs.getApplicationUrl());
	}

	private static ProjectConfigs InitializeConfig() {
		try {
			String rootDirPath = System.getProperty("user.dir");
			String projectConfigPath = rootDirPath
					+ "\\src\\test\\java\\com\\restassured\\configs\\projectconfigs.json";
			JsonReader reader = new JsonReader(new FileReader(projectConfigPath));
			Gson gson = new Gson();
			return gson.fromJson(reader, ProjectConfigs.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
