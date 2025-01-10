package com.example.inspector_service.controller;

import com.example.inspector_service.dto.request.request.CreateRequestAsset;
import com.example.inspector_service.dto.response.ApiResponse;
import com.example.inspector_service.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inspector/request")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("")
    public ApiResponse createRequest(@RequestBody CreateRequestAsset createRequestAsset) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(requestService.createRequest(createRequestAsset))
                .build();
        return apiResponse;
    }
    @GetMapping("")
    public ApiResponse getAllRequest() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(requestService.getAllRequests())
                .build();
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse getRequestById(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(requestService.getRequestById(id))
                .build();
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse updateRequest(@PathVariable Long id,@RequestBody CreateRequestAsset createRequestAsset) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(requestService.updateRequest(id, createRequestAsset))
                .build();
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteRequest(@PathVariable Long id) {
        requestService.deleteRequestById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .build();
        return apiResponse;
    }

}
