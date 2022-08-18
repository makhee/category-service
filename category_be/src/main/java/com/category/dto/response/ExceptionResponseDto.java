package com.category.dto.response;

import com.category.enums.code.ExceptionCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponseDto {
    private HttpStatus status;

    private String code;

    private String message;

    public ExceptionResponseDto(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getStatus();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public ExceptionResponseDto(ExceptionCode exceptionCode, String message) {
        this.status = exceptionCode.getStatus();
        this.code = exceptionCode.getCode();
        this.message = message;
    }
}