package com.restassured.setup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectConfigs
{
	private String applicationUrl;
	private String browserName;
	private String browserVersion;
	private String browserScope;
	private String environment;
	private String featureFolderName;
	private String featureName;
	private String scenarioName;
	private long webDriverMaxWait;
	private int implicitWait;
	private int pageLoadWait;
	private boolean headlessBrowser;
	private String fileDownloadPath;
	private boolean incognitoMode;
	private boolean screenCapture;
	private String outputRespPojoDir;
	private String outputRespPojoPkg;
}
