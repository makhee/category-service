package com.category.exception;

import com.category.enums.code.ExceptionCode;

public class ValidationException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public ValidationException(String message) {
        super(message);
        this.exceptionCode = ExceptionCode.VALIDATION_EXCEPTION;
    }

    public ValidationException() {
        super();
        this.exceptionCode = ExceptionCode.VALIDATION_EXCEPTION;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}