package com.wondrous.board.global.exception;


public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }

    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

}
