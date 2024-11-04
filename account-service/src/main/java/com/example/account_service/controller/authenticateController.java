package com.example.account_service.controller;

import com.example.account_service.dto.request.authenticate.LoginRequest;
import com.example.account_service.dto.response.ApiResponse;
import com.example.account_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class authenticateController {
    @Autowired
    AuthService authService;
    @PostMapping("")
    public ApiResponse authenticate(@RequestBody LoginRequest loginRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(authService.login(loginRequest))
                .build();
        return apiResponse;
    }
}
