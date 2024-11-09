package com.wondrous.board.domain.posts.dto.response;

import com.wondrous.board.domain.posts.eneity.Article;

import java.util.Date;

public class SearchedArticleSimpleResponseDto {

    private Long id;
    private String title;
    private String author;
    private Date createdDate;

    public SearchedArticleSimpleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.createdDate = article.getCreateDate();
    }

}
