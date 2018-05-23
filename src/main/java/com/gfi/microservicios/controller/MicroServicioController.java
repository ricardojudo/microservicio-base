package com.gfi.microservicios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/v1/api/microservicio")

public class MicroServicioController {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String metodo() {
		return "una cadena";
	}
}
