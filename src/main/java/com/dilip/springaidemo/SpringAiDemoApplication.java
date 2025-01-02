package com.dilip.springaidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiDemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringAiDemoApplication.class);
		app.setWebApplicationType(WebApplicationType.REACTIVE);
		SpringApplication.run(SpringAiDemoApplication.class, args);
	}

}
