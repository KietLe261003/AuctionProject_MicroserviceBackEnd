package com.example.account_service.dto.request.user;

import lombok.*;

@Data
@Getter
@Setter
public class UserCreationByAdmin {
    private String name;       // Tên người dùng
    private String email;      // Email
    private String address;    // Địa chỉ
    private String phone;      // Số điện thoại
    private Boolean gender;    // Giới tính (true = Nam, false = Nữ)
    private Long role;      // Vai trò (3 = Staff, 2 = Admin)
}
