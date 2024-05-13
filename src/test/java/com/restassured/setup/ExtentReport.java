package com.restassured.setup;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;

import lombok.Getter;
import lombok.Setter;

public class ExtentReport {
	@Getter
	@Setter
	public WebDriver driver;
	
	public ExtentReport()
	{
		
	}
	
	public ExtentReport(ThreadLocal<WebDriver> driver)
	{
		setDriver(driver.get());
	}
	
	public ExtentReport(WebDriver driver)
	{
		setDriver(driver);
	}
	
	public void LogResult(boolean hasFailures, String message)
	{
		if (Hooks.testReport.get() == null)
		{
			return;
		}
		
		String result = hasFailures ? "FAIL" : "PASS";
		Status logStatus = hasFailures ? Status.FAIL : Status.PASS; 
		Hooks.testReport.get().log(logStatus, "Result: " + result + " | " + "Details: " + message);
		if (getDriver() != null && hasFailures && Hooks.projectConfigs.isScreenCapture())
		{
			SaveScreenshot();
		}
	}
	
	public void SaveScreenshot()
	{
		if (Hooks.testReport.get() == null)
		{
			return;
		}
		
		int width = 250;
		int height = 150;
		TakesScreenshot screenshotDriver = (TakesScreenshot) getDriver();
		
		try
		{
			String imageBinary = screenshotDriver.getScreenshotAs(OutputType.BASE64);
			Hooks.testReport.get().log(Status.INFO,
					"<a href=data:image/png;base64," + imageBinary + " data-featherlight=\"image\"> <img alt=\"Embedded Image\" src=\"data:image/png;base64," + imageBinary +"\"width\"" + width + "\"height\""+ height + "\"/></a>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static ExtentReport ToSeleniumReport(WebDriver driver)
	{
		try
		{
			return new ExtentReport(driver);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
