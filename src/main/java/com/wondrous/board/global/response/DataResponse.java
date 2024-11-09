package com.wondrous.board.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DataResponse<T> extends DefaultResponse {

    /**
     * 상태 코드와 메시지, 데이터를 함께 보내는 경우
     * @param <T>
     */
    private final T data;
    private DataResponse(HttpStatus status, String message, T data) {
        super(status.value(), message);
        this.data = data;
    }

    public static <T> DataResponse<T> of(HttpStatus status, String message, T data) {
        return new DataResponse<>(status, message, data);
    }

}
