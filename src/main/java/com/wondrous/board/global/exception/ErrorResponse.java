package com.wondrous.board.global.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter


public class ErrorResponse {

    private String message;
    private String code;

    //errorCode의 전체 enum 형태의
    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getCode();
    }
    //사용자 정의 message를 허용하는 생성자
    public ErrorResponse(final ErrorCode code, final String message) {
        this.message = message;
        this.code = code.getCode();
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final String message) {
        return new ErrorResponse(code, message);
    }
}
