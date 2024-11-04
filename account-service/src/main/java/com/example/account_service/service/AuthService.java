package com.example.account_service.service;

import com.example.account_service.base_exception.AppException;
import com.example.account_service.base_exception.ErrorCode;
import com.example.account_service.dto.request.authenticate.LoginRequest;
import com.example.account_service.respository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRespository respository;
    public Boolean login(LoginRequest loginRequest) {
        var user = respository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new AppException(ErrorCode.Not_Found_Email));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());

    }
}
