package com.gfi.microservicios.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class DummyOAuth2RestTemplateClient {

	@Autowired
	OAuth2RestTemplate restTemplate;
	
	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @HystrixCommand(fallbackMethod="respuestaFallBackMensaje",
//    		threadPoolKey="dummyMensajeThreadPool2",
//    		threadPoolProperties= {
//    				@HystrixProperty(name="coreSize",value="30"),
//    				@HystrixProperty(name="maxQueueSize",value="10"),
//    		},
//    		commandProperties={
//    				@HystrixProperty (
//    				   name="execution.isolation.thread.timeoutInMilliseconds",
//    	               value="100"
//    	      )
//              }
//    		)
	
	public String getMensaje() {
		logger.info("Invocado  con OAuth2 a Dummy mensaje");

		String url="http://microservicio-dummy/dummy/mensaje";

		ResponseEntity<String> restExchange = 
				restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return restExchange.getBody();
	}
    
    
//    @HystrixCommand(fallbackMethod="respuestaFallBackSaludo",
//    		threadPoolKey="dummySaludoThreadPool2",
//    		threadPoolProperties= {
//    				@HystrixProperty(name="coreSize",value="15"),
//    				@HystrixProperty(name="maxQueueSize",value="5"),
//    		},
//    		commandProperties={
//    				@HystrixProperty (
//    				   name="execution.isolation.thread.timeoutInMilliseconds",
//    	               value="100"
//    	      )
//              }
//    		)
	
	public String getSaludo() {
		logger.info("Invocado  con OAuth2 a Dummy saludo");

		String url="http://microservicio-dummy/dummy/saludo";

		ResponseEntity<String> restExchange = 
				restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		return restExchange.getBody();
	}
    private String respuestaFallBackMensaje() {
    	return "dummy no está disponible para mensaje";
    }
    
    private String respuestaFallBackSaludo() {
    	return "dummy no está disponible para saludo";
    }

}

