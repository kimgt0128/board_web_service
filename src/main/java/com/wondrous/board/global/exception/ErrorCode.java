package com.wondrous.board.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public enum ErrorCode {
    //Commen
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "E1", "올바르지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "E2", "잘못된 HTTP 메서드를 호출했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E3", "서버 에러가 발생했습니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "E4", "올바르지 않은 타입입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "E4", "존제하지 않는 엔티티입니다.");

    //Post

    //Member

    //Business

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;


    //Constructor
    ErrorCode(HttpStatus httpStatus, String code, String message) {
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
