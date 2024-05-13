package com.restassured.pojoutilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import com.restassured.fileReader.PropertyReader;
import com.sun.codemodel.JCodeModel;

public class ConvertJsonToPojo {
	private File inputRespJson;
	private File outputRespPojoDir;
	private String resp_pkg;

	public ConvertJsonToPojo(String requestName) {
		Properties prop = PropertyReader.getInstance();
		resp_pkg = prop.getProperty(requestName + "_respPkg");
		String resp_inputRespJson = prop.getProperty(requestName + "_inputRespJson");
		String resp_outputRespPojoDir = prop.getProperty(requestName + "_outputRespPojoDir");
		// Get Response Package Details
		inputRespJson = new File(resp_inputRespJson);
		outputRespPojoDir = new File(resp_outputRespPojoDir);
	}

	public void ConvertRespToJson() {
		try {
			// outputRespPojoDir.mkdirs();
			ConvertRespJsonToPOJO.convertResponseJSON(inputRespJson.toURI().toURL(), outputRespPojoDir, resp_pkg,
					inputRespJson.getName().replace(".json", ""));
		} catch (IOException e) { // TODO Auto-generated catch block
			System.out.println("Encountered issue while converting to pojo: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new ConvertJsonToPojo("getuser").ConvertRespToJson();
	}
}
