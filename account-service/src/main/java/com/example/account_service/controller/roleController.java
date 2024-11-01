package com.example.account_service.controller;

import com.example.account_service.dto.response.apiResponse;
import com.example.account_service.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class roleController {
    @Autowired
    roleService service;

    @GetMapping("")
    public apiResponse getAllRoles() {
        apiResponse response = apiResponse.builder()
                .code(200)
                .message("OK")
                .data(service.findAll())
                .build();
        return response;
    }
}
