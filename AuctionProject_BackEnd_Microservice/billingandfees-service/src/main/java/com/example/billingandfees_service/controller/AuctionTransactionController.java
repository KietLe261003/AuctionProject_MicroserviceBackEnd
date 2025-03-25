package com.example.billingandfees_service.controller;

import com.example.billingandfees_service.dto.request.AuctionTransactionRequest;
import com.example.billingandfees_service.dto.request.AuctionTransactionUpdate;
import com.example.billingandfees_service.dto.request.RegisterAuctionRequest;
import com.example.billingandfees_service.dto.response.ApiResponse;
import com.example.billingandfees_service.dto.response.PaymentResponse;
import com.example.billingandfees_service.service.AuctionTransactionService;
import com.example.billingandfees_service.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bill-management-service/auctiontransaction")
public class AuctionTransactionController {
    @Autowired
    private AuctionTransactionService auctionTransactionService;

    @Autowired
    private VNPayService vnPayService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllTransactions() {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.findAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTransactionById(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.findById(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("")
    public ResponseEntity<ApiResponse> create(@RequestBody AuctionTransactionRequest auctionTransactionRequest) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.save(auctionTransactionRequest))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/payment/{auctiontransactionId}")
    public ResponseEntity<ApiResponse> getPaymentById(@PathVariable Long auctiontransactionId, HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(vnPayService.createPaymentUrlForAuction(auctiontransactionId,clientIp))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/vnpay_return")
    public ResponseEntity<PaymentResponse> handleVNPayReturn(@RequestParam Map<String, String> params) {
        // Gọi service để xử lý và trả về kết quả
        String result = vnPayService.processVNPayResponseAuction(params);

        PaymentResponse response;
        if (result.contains("Payment Success")) {
            response = new PaymentResponse("Payment successful", result, true);
        } else {
            response = new PaymentResponse("Payment failed", result, false);
        }

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody AuctionTransactionUpdate auctionTransactionUpdate) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.update(id, auctionTransactionUpdate))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        auctionTransactionService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/checktransaction/{auctionId}/{userId}")
    public ResponseEntity<ApiResponse> checkTransaction(@PathVariable Long auctionId, @PathVariable Long userId) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.checkTransactionByAuctionIdAndUserId(auctionId, userId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<ApiResponse> findByUserId(@PathVariable Long id) {
        ApiResponse apiResponse = ApiResponse.builder()
                .Code(200)
                .Message("Success")
                .Data(auctionTransactionService.findByUserId(id))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
