package com.resttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.resttest"})
public class RestTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTestApplication.class, args);
	}

}
