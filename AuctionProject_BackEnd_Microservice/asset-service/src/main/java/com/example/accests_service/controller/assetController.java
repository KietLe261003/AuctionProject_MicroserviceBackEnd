package com.example.accests_service.controller;

import com.example.accests_service.dto.response.apiResponse;
import com.example.accests_service.service.assetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/asset")
public class assetController {
    @Autowired
    assetService service;
    @GetMapping("")
    public apiResponse getAllAssets() {
        apiResponse response = apiResponse.builder()
                .code(200)
                .message("OK")
                .data(service.findAll())
                .build();
        return response;
    }
}
