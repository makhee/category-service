package com.category.exception.handler;

import com.category.dto.response.ExceptionResponseDto;
import com.category.enums.code.ExceptionCode;
import com.category.exception.DatabaseException;
import com.category.exception.NoSearchResultException;
import com.category.exception.SystemException;
import com.category.exception.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ExceptionResponseDto> handleNoHandlerFoundException(NoHandlerFoundException noHandlerFoundException,
                                                                                 HttpServletRequest request) {
        ExceptionCode exceptionCode = ExceptionCode.NOT_FOUND_EXCEPTION;
        return new ResponseEntity<>(this.getResponseDto(noHandlerFoundException, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        커스텀 DB 오류
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ExceptionResponseDto> handleDatabaseException(DatabaseException databaseException) {
        ExceptionCode exceptionCode = databaseException.getExceptionCode();
        return new ResponseEntity<>(this.getResponseDto(databaseException, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        커스텀 DB 결과 없는경우
     */
    @ExceptionHandler(NoSearchResultException.class)
    public ResponseEntity<ExceptionResponseDto> handleNoSearchResultException(NoSearchResultException noSearchResultException) {
        ExceptionCode exceptionCode = noSearchResultException.getExceptionCode();
        return new ResponseEntity<>(this.getResponseDto(noSearchResultException, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        커스텀 VALIDATION ERROR
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException validationException) {
        ExceptionCode exceptionCode = validationException.getExceptionCode();
        return new ResponseEntity<>(this.getResponseDto(validationException, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        커스텀 SYSTEM ERROR
     */
    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ExceptionResponseDto> handleSystemException(SystemException systemException) {
        ExceptionCode exceptionCode = systemException.getExceptionCode();
        return new ResponseEntity<>(this.getResponseDto(systemException, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        DTO 검증 실패
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponseDto> handleDtoValidationException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        ExceptionCode exceptionCode = ExceptionCode.DTO_VALIDATION_EXCEPTION;
        return new ResponseEntity<>(this.getResponseDto(exceptionCode, this.getMessage(methodArgumentNotValidException.getBindingResult())),
                exceptionCode.getStatus());
    }

    /*
        PathVariable 값 검증 실패
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponseDto> handleConstraintViolationException(
            ConstraintViolationException constraintViolationException) {

        ConstraintViolation<?> violation = constraintViolationException.getConstraintViolations().iterator().next();

        ExceptionCode exceptionCode = ExceptionCode.CONSTRAINT_VIOLATION_EXCEPTION;
        return new ResponseEntity<>(this.getResponseDto(exceptionCode, this.getMessage(violation)),
                exceptionCode.getStatus());
    }

    /*
        @Valid 예외
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponseDto> handleMethodArgumentTypeMismatchException (
            MethodArgumentTypeMismatchException methodArgumentNotValidException) {

        ExceptionCode exceptionCode = ExceptionCode.CONSTRAINT_VIOLATION_EXCEPTION;
        return new ResponseEntity<>(this.getResponseDto(exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        Exception 은 마지막에 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException(Exception exception) {
        ExceptionCode exceptionCode = ExceptionCode.SYSTEM_EXCEPTION;
        return new ResponseEntity<>(this.getResponseDto(exception, exceptionCode),
                exceptionCode.getStatus());
    }

    /*
        DTO Validation 검증 실패 메시지
     */
    private String getMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().get(0).getDefaultMessage();
    }

    /*
        PathVariable Validation 검증 실패 메시지
     */
    private String getMessage(ConstraintViolation<?> violation) {
        return violation.getMessage();
    }

    private ExceptionResponseDto getResponseDto(ExceptionCode exceptionCode, String message) {
        return new ExceptionResponseDto(exceptionCode, message);
    }

    private ExceptionResponseDto getResponseDto(ExceptionCode exceptionCode) {
        String message = "시스템 오류";

        return new ExceptionResponseDto(exceptionCode, message);
    }

    private ExceptionResponseDto getResponseDto(RuntimeException re, ExceptionCode exceptionCode) {
        if (re.getMessage() == null || re.getMessage().isEmpty()) {
            return new ExceptionResponseDto(exceptionCode);
        }
        return new ExceptionResponseDto(exceptionCode, re.getMessage());
    }

    private ExceptionResponseDto getResponseDto(Exception e, ExceptionCode exceptionCode) {
        if (e.getMessage() == null || e.getMessage().isEmpty()) {
            return new ExceptionResponseDto(exceptionCode);
        }
        return new ExceptionResponseDto(exceptionCode, e.getMessage());
    }

    private ExceptionResponseDto getResponseDto(Exception e, ExceptionCode exceptionCode, String message) {
        if (message == null || message.isEmpty()) {
            return new ExceptionResponseDto(exceptionCode);
        }
        return new ExceptionResponseDto(exceptionCode, message);
    }
}
