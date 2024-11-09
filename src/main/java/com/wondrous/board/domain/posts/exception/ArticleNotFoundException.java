package com.wondrous.board.domain.posts.exception;


import com.wondrous.board.global.exception.ErrorCode;
import com.wondrous.board.global.exception.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {
    public ArticleNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
