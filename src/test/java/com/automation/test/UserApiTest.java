package com.automation.test;

import javax.ws.rs.client.Invocation.Builder;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pojo.UserDetails;

public class UserApiTest extends BaseApi{
	
	
	String email = "aaaaaa"+System.currentTimeMillis()+"@gmail.com";
	
	@Test(priority=1)
	public void createUserTest(){
		
		Builder builder=creaBuilder("http://localhost:8080/user-service/webapi/service/addUser/");
		builder.header("Content-Type","application/json");
		UserDetails user = new UserDetails();
		user.setAddress(null);
		user.setEmail(email);
		user.setFirstName("laasya");
		user.setLastName("nagabandi");
		
		String response=builder.post(javax.ws.rs.client.Entity.json(user)).readEntity(String.class);;
		
		System.out.println(response);
		
		Assert.assertTrue(response!=null);
	}
	
	@Test(priority=2)
	public void getUserTest(){
		
		Builder builder=creaBuilder("http://localhost:8080/user-service/webapi/service/getUser/"+email);
		builder.header("Content-Type","application/json");
		UserDetails user =builder.get().readEntity(UserDetails.class);
		System.out.println(user.getFirstName());
		System.out.println(user.getEmail());
		System.out.println(user.getLastName());
		Assert.assertTrue(user.getEmail()!=null);
	}
	
	@Test(priority=3)
	public void deleteUserTest(){
		
		Builder builder=creaBuilder("http://localhost:8080/user-service/webapi/service/deleteUser/"+email);
		builder.header("Content-Type","application/json");
		String response=builder.delete().readEntity(String.class);
		Assert.assertTrue(response!=null);
		
	}
	
	
	public static void main(String[] args) {
		
		
	}

}
