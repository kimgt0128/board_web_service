package com.wondrous.board.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class DefaultResponse {
    // 응답 코드
    protected int status;
    // 응답 메시지
    protected String message;

    protected DefaultResponse(int status, String message)  {
        this.status = status;
        this.message = message;
    }
}
