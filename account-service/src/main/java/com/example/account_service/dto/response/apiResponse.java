package com.example.account_service.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class apiResponse {
    private int code;
    private String message;
    private Object data;
}
