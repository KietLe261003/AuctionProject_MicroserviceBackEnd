package com.example.account_service.controller;

import com.example.account_service.dto.response.apiResponse;
import com.example.account_service.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class userController {
    @Autowired
    userService service;
    @GetMapping("")
    public apiResponse getAllUser() {
        apiResponse response = apiResponse.builder()
                .code(200)
                .message("success")
                .data(service.findAll())
                .build();
        return response;
    }
}
