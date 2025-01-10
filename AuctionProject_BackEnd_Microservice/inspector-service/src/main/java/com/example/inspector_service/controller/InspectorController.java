package com.example.inspector_service.controller;

import com.example.inspector_service.dto.request.inspector.CreateInspectorRequest;
import com.example.inspector_service.dto.response.ApiResponse;
import com.example.inspector_service.service.InspectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inspector")
public class InspectorController {
    @Autowired
    private InspectorService inspectorService;
    @PostMapping("")
    public ApiResponse createInspector(@RequestBody CreateInspectorRequest createInspectorRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(inspectorService.createInspector(createInspectorRequest))
                .build();
        return apiResponse;
    }
    @GetMapping("")
    public ApiResponse getAllInspectors() {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(inspectorService.findAll())
                .build();
        return apiResponse;
    }
    @GetMapping("/{id}")
    public ApiResponse getInspectorById(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("Success")
                .data(inspectorService.getInspectorById(id))
                .build();
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse updateInspector(@PathVariable Long id, @RequestBody CreateInspectorRequest createInspectorRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .data(inspectorService.updateInspector(id,createInspectorRequest))
                .build();
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteInspector(@PathVariable Long id) {
        inspectorService.deleteInspector(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .code(200)
                .message("success")
                .build();
        return apiResponse;
    }
}
