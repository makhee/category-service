package com.category.enums.code;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ExceptionCode {
    // 4xx
    NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "NOT_FOUND_EXCEPTION", ""),
    // 5xx
    VALIDATION_EXCEPTION(HttpStatus.NOT_EXTENDED, "VALIDATION_EXCEPTION", "유효성 검사 오류"),
    NO_SEARCH_RESULT_EXCEPTION(HttpStatus.NOT_EXTENDED, "NO_SEARCH_RESULT_EXCEPTION", ""),
    DATABASE_EXCEPTION(HttpStatus.NOT_EXTENDED, "DATABASE_EXCEPTION", "데이터베이스 오류"),
    SYSTEM_EXCEPTION(HttpStatus.NOT_EXTENDED, "SYSTEM_EXCEPTION", "시스템 오류"),
    // 이하 Parameter validation exception handler
    DTO_VALIDATION_EXCEPTION(HttpStatus.NOT_EXTENDED, "DTO_VALIDATION_EXCEPTION", "데이터 유효성 검사 오류"),
    CONSTRAINT_VIOLATION_EXCEPTION(HttpStatus.NOT_EXTENDED, "PATH_VARIABLE_EXCEPTION", "데이터 제약 조건 유효성 오류");

    private final HttpStatus status;
    private final String code;
    private final String message;

    public HttpStatus getStatus() {
        return this.status;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}