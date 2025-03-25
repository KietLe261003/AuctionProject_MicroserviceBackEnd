package com.example.billingandfees_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillingandfeesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingandfeesServiceApplication.class, args);
	}

}
