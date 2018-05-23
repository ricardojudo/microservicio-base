package com.gfi.microservicios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/v1/api/microservicio")

public class MicroServicioController {

	@RequestMapping(value = "/metodo")
	public String metodo() {
		return "una cadena";
	}
}
