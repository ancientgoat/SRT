package com.premierinc;

import com.premierinc.server.DumbFHIRRestfulServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 */
@SpringBootApplication
public class SrtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrtApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new DumbFHIRRestfulServer(), "/dumb/*");
	}
}
