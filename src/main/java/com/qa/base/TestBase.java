package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;
	public String serviceUrl;
	public String apiUrl;
	public String url;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUp() {
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		// https://reqres.in/api/users
		url = serviceUrl + apiUrl;

	}	

}
