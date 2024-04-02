package com.app;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.app.*")
public class Application {

	public static void main(String[] args){

		new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET)
				.run(args);

	}



}
