package com.category.exception;

import com.category.enums.code.ExceptionCode;

public class NoSearchResultException extends RuntimeException{
    private final ExceptionCode exceptionCode;

    public NoSearchResultException(String message) {
        super(message);
        this.exceptionCode = ExceptionCode.NO_SEARCH_RESULT_EXCEPTION;
    }

    public NoSearchResultException() {
        super();
        this.exceptionCode = ExceptionCode.NO_SEARCH_RESULT_EXCEPTION;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}