package com.wondrous.board.domain.posts.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchedArticlesSimpleResponseDto {

    private final List<SearchedArticleSimpleResponseDto> articles;

    public SearchedArticlesSimpleResponseDto(List<SearchedArticleSimpleResponseDto> posts) {
        this.articles = posts;
    }
}
