package com.kgisl.webfluxRH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition

public class WebfluxRhApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxRhApplication.class, args);
	}

}
