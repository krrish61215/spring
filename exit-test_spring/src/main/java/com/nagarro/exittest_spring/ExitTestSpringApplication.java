package com.nagarro.exittest_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nagarro.exittest_spring"})
@CrossOrigin

public class ExitTestSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExitTestSpringApplication.class, args);
	}
	

}
