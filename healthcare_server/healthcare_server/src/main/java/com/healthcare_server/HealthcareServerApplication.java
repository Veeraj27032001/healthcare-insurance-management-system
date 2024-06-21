package com.healthcare_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthcareServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareServerApplication.class, args);
		System.out.println("welcome");
	}

}
