package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.BillItemRequest;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.entity.BillItem;
import com.example.billingandfees_service.service.BillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill-management-service/billitem")
public class BillItemController {
    @Autowired
    private BillItemService billItemService;
    @GetMapping("/{billId}")
    public ApiResponse getAllBillItems(@PathVariable Long billId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billItemService.getAllBillItemsByBillId(billId))
                .build();
        return apiResponse;
    }
    @PostMapping("/{billId}")
    public ApiResponse addBillItem(@PathVariable Long billId, @RequestBody BillItemRequest billItemRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billItemService.createBillItem(billId, billItemRequest))
                .build();
        return apiResponse;
    }
    @PutMapping("/{billId}/{billItemId}")
    public ApiResponse updateBillItem(@PathVariable Long billId,@PathVariable Long billItemId, @RequestBody BillItemRequest billItemRequest){
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billItemService.updateBillItem(billId,billItemId, billItemRequest))
                .build();
        return apiResponse;
    }
    @DeleteMapping("/{billId}/{billItemId}")
    public ApiResponse deleteBillItem(@PathVariable Long billId,@PathVariable Long billItemId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(billItemService.deleteBillItem(billId,billItemId))
                .build();
        return apiResponse;
    }
}
