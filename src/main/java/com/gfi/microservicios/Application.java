package com.gfi.microservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableResourceServer
public class Application {
	
   @LoadBalanced	
   @Bean
    public RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	 
}
