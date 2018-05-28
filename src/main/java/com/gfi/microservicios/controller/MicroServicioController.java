package com.gfi.microservicios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gfi.microservicios.client.DummyOAuth2RestTemplateClient;





@RestController
@RequestMapping(value = "/microservicio")

public class MicroServicioController {
		

	@Autowired
	DummyOAuth2RestTemplateClient dummyOAuth2RestTemplateClient; 
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="",method = RequestMethod.GET)
	public String metodo() {
		return "una cadena de un microservicio registrado con Eureka";
	}
	
 
	
	
	@RequestMapping(value="/v3/mensaje",method = RequestMethod.GET)
	public String metodoDummyMensajev3() {
		String resultado= "hola dummy saludo";
		resultado=dummyOAuth2RestTemplateClient.getMensaje();
		if (resultado==null) resultado= "hola dummy mensaje por omision";
		logger.info(resultado);
		return resultado;
	}
	
	@RequestMapping(value="/v3/saludo",method = RequestMethod.GET)
	public String metodoDummySaludov3() {
		String resultado= "hola dummy saludo";
		resultado=dummyOAuth2RestTemplateClient.getSaludo();
		if (resultado==null) resultado= "hola dummy saludo por omision";
		logger.info(resultado);
		return resultado;
	}
	

}
