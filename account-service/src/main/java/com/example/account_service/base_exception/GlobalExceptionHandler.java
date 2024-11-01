package com.example.account_service.base_exception;

import com.example.account_service.dto.response.apiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<apiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        apiResponse res = new apiResponse();

        res.setCode(errorCode.getCode());
        res.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(res);
    }
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity handleRuntimeException(RuntimeException e) {
        apiResponse Res = apiResponse.builder()
                .code(400)
                .message("Request bad")
                .data(e.getMessage()).build();
        return ResponseEntity.status(400).body(Res);
    }



}
