package com.restassured.browserDriverConfiguration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.restassured.hook.Hooks;

public class ChromeDriverManager extends DriverManager  {
	
	private ChromeDriverService chService;

   
    public void startService() {
        if (null == chService) {
            try {
                chService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(System.getProperty("user.dir")+Hooks.getBrowserEnv().get("BrowserPath").get(0)))
                    .usingAnyFreePort()
                    .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

  
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();
        	System.err.println("#### Service is stopped ####");
    }

	// public WebDriver getDriver() {
    public void createDriver() {	
	        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("test-type");
	        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	        driver = new ChromeDriver(chService, capabilities);
	        driver.manage().window().maximize();
	        long time = Long.valueOf(Hooks.getBrowserEnv().get("ImplicitWait").get(0));
	        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	        driver.get(Hooks.getBrowserEnv().get("URL").get(0));
	       // driver = new ChromeDriver();
    }

}
