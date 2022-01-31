package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase {

	RestClient restClient;

	@BeforeMethod
	public void initialize() {
		setUp();

	}

	@Test
	public void getApiTestWithOutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(url);
	}
	
	

}
