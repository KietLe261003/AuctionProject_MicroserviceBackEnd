package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.RegisterAuctionRequest;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.dto.response.PaymentResponse;
import com.example.billingandfees_service.service.RegisterAuctionService;
import com.example.billingandfees_service.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bill-management-service/registerauction")
public class RegisterAuctionController {
    @Autowired
    private RegisterAuctionService registerAuctionService;

    @Autowired
    private VNPayService vnPayService;
    @GetMapping("")
    public ResponseEntity<ApiResponse> getRegisterAuctionService() {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(registerAuctionService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<ApiResponse> getRegisterAuctionServiceByUserId(@PathVariable Long userId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(registerAuctionService.findByUserId(userId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/findByAuctionId/{auctionId}")
    public ResponseEntity<ApiResponse> getRegisterAuctionServiceByAuctionId(@PathVariable Long auctionId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(registerAuctionService.findByAuctionId(auctionId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/checkUserIdAndAuctionId/{auctionId}/{userId}")
    public ResponseEntity<ApiResponse> checkUserIdAndAuctionId(@PathVariable Long auctionId, @PathVariable Long userId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(registerAuctionService.exitsByUserIdAndByAuctionId(userId, auctionId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createRegister(@RequestBody RegisterAuctionRequest registerAuctionRequest, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(vnPayService.createPaymentUrl(registerAuctionRequest,clientIp))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRegister(@PathVariable Long id) {
        registerAuctionService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vnpay_return")
    public ResponseEntity<PaymentResponse> handleVNPayReturn(@RequestParam Map<String, String> params) {
        // Gọi service để xử lý và trả về kết quả
        String result = vnPayService.processVNPayResponse(params);

        PaymentResponse response;
        if (result.contains("Payment Success")) {
            response = new PaymentResponse("Payment successful", result, true);
        } else {
            response = new PaymentResponse("Payment failed", result, false);
        }

        return ResponseEntity.ok(response);
    }



}
