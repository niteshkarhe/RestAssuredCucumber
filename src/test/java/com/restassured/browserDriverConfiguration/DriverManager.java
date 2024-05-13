package com.restassured.browserDriverConfiguration;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            stopService();
            driver = null;
        }

    }
    public void closeDriver() {
        if (null != driver) {
            driver.close();
            driver = null;
            System.err.println("#### Driver is closed successfully ####");
        }
    }
    
    public void getService() 
    {
    	startService();
    }
    
    public WebDriver getDriver() {
    	
        if (null == driver) {
        	//startService();
            createDriver();
        }
        return driver;
    }
}
