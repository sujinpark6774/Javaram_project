package com.test.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.test.example"})
public class VueSpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueSpringTestApplication.class, args);
	}

}
