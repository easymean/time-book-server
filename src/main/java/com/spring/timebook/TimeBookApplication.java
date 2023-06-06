package com.spring.timebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.spring.timebook.config")
public class TimeBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeBookApplication.class, args);
	}

}
