package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1.GET method

	public void get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // http get request
		CloseableHttpResponse CloseableHttpResponse = httpClient.execute(httpGet); // hit the GET URL

		// a.status code:
		int statusCode = CloseableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code--->" + statusCode);

		// b.Json String:
		String responseString = EntityUtils.toString(CloseableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from Api---->" + responseJson);

		// c.all Headers:
		Header[] headersArray = CloseableHttpResponse.getAllHeaders();

		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}

		System.out.println("Headers Array--->" + allHeaders);

	}
}
