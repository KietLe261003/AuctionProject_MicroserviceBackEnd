package com.example.account_service.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    Long id;
    String name;
    String password;
    String address;
    Boolean gender;
    String email;
    String phone;
    Long role;
}
