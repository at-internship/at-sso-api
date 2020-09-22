package com.resources.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ControllerAplication {
	RestTemplate restTemplate; 
	
	@RequestMapping(value = "/")
	public String main() {
	     System.err.println("Hola");
	      return "Hello at-sso-api";
	   }
}
