package com.category.exception;

import com.category.enums.code.ExceptionCode;

public class SystemException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public SystemException(String message) {
        super(message);
        this.exceptionCode = ExceptionCode.SYSTEM_EXCEPTION;
    }

    public SystemException() {
        super();
        this.exceptionCode = ExceptionCode.SYSTEM_EXCEPTION;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}