package com.example.account_service.base_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    User_NOT_FOUND(1000,"User Not found",HttpStatus.NOT_FOUND),
    Role_NOT_FOUND(1001,"Role Not found",HttpStatus.NOT_FOUND),
    Not_Found_Email(1002,"Not Found Email",HttpStatus.NOT_FOUND),
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
