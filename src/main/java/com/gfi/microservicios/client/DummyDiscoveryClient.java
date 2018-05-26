package com.gfi.microservicios.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@Component
public class DummyDiscoveryClient {

	private final static String DUMMY= "microservicio-dummy";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private DiscoveryClient discoveryClient;

  
    
    @HystrixCommand(fallbackMethod="respuestaFallBackMensaje",
    		threadPoolKey="dummyMensajeThreadPool",
    		threadPoolProperties= {
    				@HystrixProperty(name="coreSize",value="30"),
    				@HystrixProperty(name="maxQueueSize",value="10"),
    		},
    		commandProperties={
    				@HystrixProperty (
    				   name="execution.isolation.thread.timeoutInMilliseconds",
    	               value="100"
    	      )
              }
    		)
    public String getDummyMensaje() {
        RestTemplate restTemplate = new RestTemplate();
 
        
        List<ServiceInstance> instances = descubrirDummy();
        
        if (instances.size()==0) return null;
        String serviceUri = String.format("%s/dummy/mensaje",instances.get(0).getUri().toString());
        logger.info(String.format("serviceURI  %s", serviceUri));
        ResponseEntity< String > restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class);
        return restExchange.getBody();
    }

    @HystrixCommand(fallbackMethod="respuestaFallBackSaludo",
    		threadPoolKey="dummySaludoThreadPool",
    		threadPoolProperties= {
    				@HystrixProperty(name="coreSize",value="15"),
    				@HystrixProperty(name="maxQueueSize",value="5"),
    		},
    		commandProperties={
    				@HystrixProperty (
    				   name="execution.isolation.thread.timeoutInMilliseconds",
    	               value="100"
    	      )
              }
    		)
    public String getDummySaludo() {
        RestTemplate restTemplate = new RestTemplate();
 
        
        List<ServiceInstance> instances = descubrirDummy();
        
        if (instances.size()==0) return null;
        String serviceUri = String.format("%s/dummy/saludo",instances.get(0).getUri().toString());
        logger.info(String.format("serviceURI  %s", serviceUri));
        ResponseEntity< String > restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class);
        return restExchange.getBody();
    }

    private String respuestaFallBackMensaje() {
    	return "dummy no está disponible para mensaje";
    }
    
    private String respuestaFallBackSaludo() {
    	return "dummy no está disponible para saludo";
    }


	private List<ServiceInstance> descubrirDummy() {
//      Esta sección de código es para probar 
//      List<String> servicios = discoveryClient.getServices();
//      for (String servicio : servicios) {
//      	logger.info(discoveryClient.getClass().getName());
//      	logger.info(String.format("(%s)",servicio));
//      	logger.info(discoveryClient.getInstances(servicio).toString());
//      }
//     
		List<ServiceInstance> instances = discoveryClient.getInstances(DummyDiscoveryClient.DUMMY);
        logger.info(instances.toString());
        logger.info(String.format("URI descubiertas %d", instances.size()));
		return instances;
	}
}