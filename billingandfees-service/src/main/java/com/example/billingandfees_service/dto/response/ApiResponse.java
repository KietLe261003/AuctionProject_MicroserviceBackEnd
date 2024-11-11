package com.example.billingandfees_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    int Code;
    String Message;
    Object Data;
}
