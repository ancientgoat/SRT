package com.premierinc;

import com.premierinc.server.DumbFHIRRestfulServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 *
 */
@SpringBootApplication
public class SrtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrtApplication.class, args);
	}

	/**
	 * Register the FHIR HAPI Java Servlet 3.0 with Spring Boot, for all
	 * 	requests prefixed with /dumb/*
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new DumbFHIRRestfulServer(), "/dumb/*");
	}
}
