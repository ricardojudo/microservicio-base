package com.gfi.microservicios.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gfi.microservicios.client.DummyDiscoveryClient;

@RestController
@RequestMapping(value = "/microservicio")

public class MicroServicioController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	DummyDiscoveryClient dummyDiscoveryClient;
	
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public String metodo() {
		return "una cadena de un microservicio registrado con Eureka";
	}
	
	@RequestMapping(value="/dummy",method = RequestMethod.GET)
	public String metodoDummy() {
		String resultado= "hola dummy";
		resultado=dummyDiscoveryClient.getDummy();
		if (resultado==null) resultado= "hola dummy";
		logger.info(resultado);
		return resultado;
	}
}
