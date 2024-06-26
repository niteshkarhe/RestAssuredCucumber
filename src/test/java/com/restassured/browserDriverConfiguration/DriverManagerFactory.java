package com.restassured.browserDriverConfiguration;

import com.restassured.enums.BrowserDriver;

public class DriverManagerFactory {

	private static ChromeDriverManager chromeDriver = null;

	private static FirefoxDriverManager fireDriver = null;

	public static DriverManager getManager(String browserDriverType) {
		if (browserDriverType.equalsIgnoreCase(BrowserDriver.CHROME.toString())) {
			if (chromeDriver == null) {
				chromeDriver = new ChromeDriverManager();
				return chromeDriver;
			} else
				return chromeDriver;
		} else if (browserDriverType.equalsIgnoreCase(BrowserDriver.FIREFOX.toString())) {
			if (fireDriver == null) {
				fireDriver = new FirefoxDriverManager();
				return fireDriver;
			} else
				return fireDriver;
		}
		return null;
	}
}
