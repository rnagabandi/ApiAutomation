package com.automation.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.Assert;

import com.automation.pojo.UserDetails;

public class UserApiHttpClientAutomation {

	static String email = "aaaaaa" + System.currentTimeMillis() + "@gmail.com";

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		DefaultHttpClient clinet = new DefaultHttpClient();

		HttpPost post = new HttpPost(
				"http://localhost:8080/user-service/webapi/service/addUser/");

		UserDetails user = new UserDetails();
		user.setAddress(null);
		user.setEmail(email);
		user.setFirstName("laasya");
		user.setLastName("nagabandi");

		post.addHeader("Accept", "application/json");
		post.addHeader("Content-Type", "application/json");
		//post.setEntity(user);
		HttpResponse response = clinet.execute(post);

		Assert.assertTrue(response.getStatusLine().getStatusCode() == 200);

	}

}
