package com.wondrous.board.global.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception을 핸들링하는 클래스.
 * Controller까지 올라온 Exception을 처리한다.
 */
@Getter
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter에서 등록한 HttpMessageConverter binding 못할 경우 발생한다.
     * 주로 @requestBody, @RequestsPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        return createErrorResponseEntity(ErrorCode.INVALID_TYPE_VALUE);
    }

    /**
     * HttpMessageConverter에서 등록한 HttpMessageConverter binding 못할 경우 발생한다.
     * 주로 int형으로 받아야 하는데 문자열로 받을 경우 발생
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageConversionException(HttpMessageConversionException e) {
        log.error("HttpMessageConversionException", e);
        return createErrorResponseEntity(ErrorCode.INVALID_INPUT_VALUE);
    }

    /**
     * 개발자가 정의한 사용자 Error를 처리한다.
     * 사용자가 정의한 Exception이 만약 BusinessException을 상속하고 있다면,
     * 해당 Handler가 처리하게 된다.
     * 개발자가 정의한 HttpStatus에 맞게 처리한다.
     * 개발자가 정의한 Exception은 Warn 로그를 가진다.
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.error("BusinessException", e);
        return createErrorResponseEntity(e.getErrorCode());
    }


    /**
     * 메서드마다 return new Response<>(response, HttpStatus.status)의 반복되는 형태를 모듈화한 코드
     */
    private ResponseEntity<ErrorResponse> createErrorResponseEntity(ErrorCode errorCode) {
        return new ResponseEntity<>(
                ErrorResponse.of(errorCode),
                errorCode.getHttpStatus()
        );
    }

}
