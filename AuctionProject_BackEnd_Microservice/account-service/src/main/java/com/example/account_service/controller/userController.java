package com.example.account_service.controller;

import com.example.account_service.dto.request.user.UserCreation;
import com.example.account_service.dto.request.user.UserUpdate;
import com.example.account_service.dto.response.ApiResponse;
import com.example.account_service.service.UserService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    UserService service;

    @PostMapping("")
    public ApiResponse creatUser(@RequestBody UserCreation userReq){
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(service.create(userReq))
                .build();
        return apiResponse;
    }

    @GetMapping("")
    public ApiResponse getAllUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) throws ParseException, JOSEException {
        String token = authorizationHeader.substring("Bearer ".length());
        ApiResponse response = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(service.findAll(token))
                .build();
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse getUserById(@PathVariable Long id) {
        ApiResponse res = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(service.findById(id))
                .build();
        return res;
    }

    @PutMapping("/{id}")
    public ApiResponse updateUser(@PathVariable Long id,@RequestBody UserUpdate userReq) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(service.update(id,userReq))
                .build();
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable Long id) {
        service.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .build();
        return apiResponse;
    }
}
