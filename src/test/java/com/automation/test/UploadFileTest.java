package com.automation.test;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class UploadFileTest {
    private final static String contentType = "multipart/form-data";

    public static Response postFile(String serverURL, File json) {
        MultiPart multiPart = null;
        try {
            
            Cookie cookie = new Cookie("aiva_interactive_tester",
                        "s%3ApjHefrcuj6jPxzTZNEu5Pc7GdwAxmz4c.b4ZrdZM2sdQEfBqqnOeMrT0Vlae3o2i9xB9rlzRiffw",
                            "qa.developer.sv2.247-inc.net","/");
            
            Client client = ClientBuilder.newBuilder().
                                register(MultiPartFeature.class).build();
            client.property(ClientProperties.PROXY_URI, "localhost:3128");
            System.setProperty("http.proxyHost","localhost");
            System.setProperty("http.proxyPort", "3128");
            /*System.setProperty("https.proxyHost","10.64.99.240");
            System.setProperty("https.proxyPort", "3128");*/
            WebTarget server = client.target(serverURL);
            multiPart = new MultiPart();
            FileDataBodyPart jsonBodyPart = new FileDataBodyPart("appConfig.json", json,
                    MediaType.MULTIPART_FORM_DATA_TYPE);
            
            multiPart.bodyPart(jsonBodyPart);                    
            Response response = server.request().property(ClientProperties.FOLLOW_REDIRECTS, true).cookie(cookie)
                    .post(Entity.entity(multiPart,MediaType.MULTIPART_FORM_DATA_TYPE));
            
            if (response.getStatus() == 200) {
                System.out.println(response);
                } else {
                System.out.println("Response is not ok");
                System.out.println(response);
            }
            
            return response;
        } catch (Exception e) {
            System.out.println("Exception has occured "+ e.getMessage());
            return null;
        } finally {
            if (null != multiPart) {
                try {
                    multiPart.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        
    }
    
    public static void main(String[] args) {
        
        String url = "https://qa.developer.sv2.247-inc.net/interactivetester/uploadAppConfig";
        File zipFile = new File("C:\\Users\\raghavender.n\\Desktop\\appConfig1.json");
        postFile(url, zipFile);
    }
}
