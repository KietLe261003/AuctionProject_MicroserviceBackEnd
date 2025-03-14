package com.example.account_service.dto.request.authenticate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckTokenRequest {
    String token;
}
