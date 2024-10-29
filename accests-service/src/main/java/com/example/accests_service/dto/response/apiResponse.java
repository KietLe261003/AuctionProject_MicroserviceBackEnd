package com.example.accests_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class apiResponse {
    private int code;
    private String message;
    private Object data;
}
