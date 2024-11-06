package com.example.account_service.controller;

import com.example.account_service.dto.response.ApiResponse;
import com.example.account_service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/role")
public class roleController {
    @Autowired
    RoleService service;

    @GetMapping("")
    public ApiResponse getAllRoles() {
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("OK")
                .data(service.findAll())
                .build();
        return response;
    }
}
