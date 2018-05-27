package com.gfi.microservicios.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DummyRestTemplateClient {
    @Autowired
	RestTemplate restTemplate;

    public String getSaludo() {
   
    	String url="http://microservicio-dummy/dummy/saludo";
    	
    	ResponseEntity<String> restExchange = 
    			restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    	return restExchange.getBody();
    }
}
