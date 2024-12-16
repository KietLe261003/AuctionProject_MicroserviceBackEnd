package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.BillRequest;
import com.example.billingandfees_service.dto.request.BillRequestUpdate;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill-management-service/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping("")
    public ApiResponse getAllBills() {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billService.getAllBills())
                .build();
        return apiResponse;
    }
    @GetMapping("/{billId}")
    public ApiResponse getAllBillsById(@PathVariable Long billId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billService.getBillById(billId))
                .build();
        return apiResponse;
    }
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
    @PutMapping("/{billId}")
    public ApiResponse updateBill(@PathVariable Long billId, @RequestBody BillRequestUpdate billRequestUpdate) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billService.updateBill(billId, billRequestUpdate))
                .build();
        return apiResponse;
    }
}
