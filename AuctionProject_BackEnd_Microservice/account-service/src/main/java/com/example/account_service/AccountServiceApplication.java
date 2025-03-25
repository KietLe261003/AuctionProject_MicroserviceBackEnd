package com.example.account_service;

import com.example.account_service.entity.Role;
import com.example.account_service.respository.RoleRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountServiceApplication {
	@Autowired
	RoleRespository roleRespository;
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
