package com.category.exception;

import com.category.enums.code.ExceptionCode;

public class DatabaseException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public DatabaseException(String message) {
        super(message);
        this.exceptionCode = ExceptionCode.DATABASE_EXCEPTION;
    }

    public DatabaseException() {
        super();
        this.exceptionCode = ExceptionCode.DATABASE_EXCEPTION;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}