package com.gfi.microservicios.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/api/microservicio")

public class MicroServicioController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String metodo() {
		return "una cadena";
	}
}
