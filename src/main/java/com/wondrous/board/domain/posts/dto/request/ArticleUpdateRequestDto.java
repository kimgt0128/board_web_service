package com.wondrous.board.domain.posts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleUpdateRequestDto {
    private Long id;
    private String title;
    private String content;
}
