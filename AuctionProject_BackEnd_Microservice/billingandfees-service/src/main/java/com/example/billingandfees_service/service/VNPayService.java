package com.example.billingandfees_service.service;


import com.example.billingandfees_service.base_exception.AppException;
import com.example.billingandfees_service.base_exception.ErrorCode;
import com.example.billingandfees_service.configuation.VNPayConfig;
import com.example.billingandfees_service.dto.request.RegisterAuctionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;


@Service
public class VNPayService {
    @Autowired
    private VNPayConfig vnPayConfig;

    @Autowired
    private RegisterAuctionService registerAuctionService;

    private Map<String, RegisterAuctionRequest> temporaryStorage = new HashMap<>();
    public String createPaymentUrl(RegisterAuctionRequest registerAuctionRequest,String  ipAddress) {

        try {
            Map<String, String> vnpParams = new HashMap<>();
            //Thông tin
            vnpParams.put("vnp_Version", "2.1.0");
            vnpParams.put("vnp_Command", "pay");
            vnpParams.put("vnp_TmnCode", vnPayConfig.getTmnCode());

            BigDecimal amount = new BigDecimal(registerAuctionRequest.getPrice()).multiply(BigDecimal.valueOf(100));
            vnpParams.put("vnp_Amount", amount.toString());
            vnpParams.put("vnp_CurrCode", "VND");
            vnpParams.put("vnp_TxnRef", generateTxnRef(registerAuctionRequest.getUserId()+""));

            vnpParams.put("vnp_OrderInfo", "Payment for "+registerAuctionRequest.getAuctionId());
            vnpParams.put("vnp_OrderType", "other");
            vnpParams.put("vnp_Locale", "vn");
            vnpParams.put("vnp_ReturnUrl", vnPayConfig.getReturnUrl());
            vnpParams.put("vnp_IpAddr", ipAddress);

            // thời hạn
            String createDate = getCurrentDate();
            vnpParams.put("vnp_CreateDate", createDate);
            String expireDate = getExpireDate(createDate);
            vnpParams.put("vnp_ExpireDate", expireDate);

            String query = buildQueryString(vnpParams);

            // Calculate the secure hash
            String secureHash = calculateHMACSHA512(query, vnPayConfig.getHashSecret());

            // Add secure hash to the query string
            query += "&vnp_SecureHash=" + secureHash;

            temporaryStorage.put(vnpParams.get("vnp_TxnRef"),registerAuctionRequest);
            return vnPayConfig.getUrl() + "?" + query;
        }catch (Exception e){
            throw new AppException(ErrorCode.Payment_Create_Failed);
        }
    }
    private String generateTxnRef(String roomId) {
        return roomId + "_" + System.currentTimeMillis();
    }
    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return formatter.format(new Date());
    }
    private String getExpireDate(String createDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return formatter.format(new Date(System.currentTimeMillis() + 300000)); // 15 minutes
    }
    private String buildQueryString(Map<String, String> vnp_Params) {
        return vnp_Params.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey()) // Sort parameters alphabetically
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
    private String calculateHMACSHA512(String data, String secretKey) throws Exception {
        Mac sha512_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        sha512_HMAC.init(secretKeySpec);
        byte[] hashBytes = sha512_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    //Cấu hình trả về khi thanh toán
    public String processVNPayResponse(Map<String, String> params) {
        String txnRef = params.get("vnp_TxnRef");
        RegisterAuctionRequest registerAuctionRequest = temporaryStorage.get(txnRef);

        if (registerAuctionRequest == null) {
            return "Invalid transaction reference!";
        }

        // Verify the security hash from VNPay
        String secureHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash"); // Remove secure hash from params to calculate it again

        String query = buildQueryString(params);

        try {
            String calculatedHash = calculateHMACSHA512(query, vnPayConfig.getHashSecret());

            if (!calculatedHash.equals(secureHash)) {
                return "Invalid payment signature!";
            }

            String responseCode = params.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                // Payment success, save course of user
                temporaryStorage.remove(txnRef); // Remove from temporary storage
                registerAuctionService.save(registerAuctionRequest);
                return "Payment Success! Ref: " + txnRef + ", Amount: " + params.get("vnp_Amount");
            } else {
                return "Payment Failed! Ref: " + txnRef + ", Amount: " + params.get("vnp_Amount");
            }
        } catch (Exception e) {
            return "Error calculating signature!";
        }
    }
}
