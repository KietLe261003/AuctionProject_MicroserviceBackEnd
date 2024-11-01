package com.example.account_service.base_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    User_NOT_FOUND(1000,"User Not found",HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    private Integer code;
    private String message;
    private HttpStatus httpStatus;
}
