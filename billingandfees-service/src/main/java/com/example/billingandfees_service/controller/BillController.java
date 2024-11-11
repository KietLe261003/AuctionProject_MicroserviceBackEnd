package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.BillRequest;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("")
    public ApiResponse create(@RequestBody BillRequest billRequest)
    {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billService.createBill(billRequest))
                .build();
        return apiResponse;
    }
}
