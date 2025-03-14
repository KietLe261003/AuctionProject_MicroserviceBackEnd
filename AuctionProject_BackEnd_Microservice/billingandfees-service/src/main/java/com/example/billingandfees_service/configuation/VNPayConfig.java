package com.example.billingandfees_service.configuation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class VNPayConfig {
    @Value("${vnpay.tmnCode}")
    private String tmnCode;  // Mã TMN của VNPay
    @Value("${vnpay.hashSecret}")
    private String hashSecret;  // Bí mật hash của VNPay
    private String url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";  // URL VNPay Sandbox
    private String returnUrl = "http://localhost:8083/bill-management-service/registerauction/vnpay_return";  // URL trả về sau khi thanh toán
}