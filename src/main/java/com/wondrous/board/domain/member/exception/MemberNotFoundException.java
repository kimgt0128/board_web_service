package com.wondrous.board.domain.member.exception;

import com.wondrous.board.global.exception.ErrorCode;
import com.wondrous.board.global.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
