package com.example.inspector_service.base_exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorCode {

    Not_Found_User(1000,"Not found User",HttpStatus.NOT_FOUND),
    Not_Found_Inspector(1001,"Not found Inspector",HttpStatus.NOT_FOUND),
    Not_Found_Request(1002,"Not found Request",HttpStatus.NOT_FOUND),
    ;
    int code;
    String message;
    HttpStatus httpStatus;
    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
