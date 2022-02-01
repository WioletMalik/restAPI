package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	TestBase testBase;

	@BeforeMethod
	public void initialize() {
		setUp();
	}

	@Test
	public void postAPI() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");

		// object to json file
		mapper.writeValue(
				new File("/Users/wioletginotra/eclipse-workspace/restApi/src/main/java/com/qa/data/users.json"), users);

		// object to json String
		String userJsonString = mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		closeableHttpResponse = restClient.post(url, userJsonString, headerMap);

		// 1.status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, TestBase.RESPONSE_STATUS_CODE_201);

		// 2. Json String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);

		// Json to java OBJ
		Users userResObj = mapper.readValue(responseString, Users.class);
		System.out.println(userResObj);

		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
	}
}
