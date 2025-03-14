package com.example.billingandfees_service.dto.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String message;
    private String paymentUrl;
    private Boolean success;
}
