package com.example.billingandfees_service.base_exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {
    NOT_FOUND_BILL(1000,"Not Found Bill",HttpStatus.NOT_FOUND);
    int code;
    String message;
    HttpStatus status;
    ErrorCode(int code, String message,HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
