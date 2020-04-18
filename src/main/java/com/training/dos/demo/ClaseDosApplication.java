package com.training.dos.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = JacksonAutoConfiguration.class)
public class ClaseDosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaseDosApplication.class, args);
	}

}
