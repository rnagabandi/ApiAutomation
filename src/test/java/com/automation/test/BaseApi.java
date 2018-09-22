package com.automation.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;

public class BaseApi {
	
	public Builder creaBuilder(String url){
		Client client = ClientBuilder.newClient();
		WebTarget target=client.target(url);
		Builder builder=target.request();
		builder.accept(MediaType.APPLICATION_JSON);
		return builder;
	}
	
}
