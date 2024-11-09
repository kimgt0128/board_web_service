package com.wondrous.board.domain.posts.dto.response;

import com.wondrous.board.domain.posts.eneity.Article;
import lombok.Getter;

@Getter
public class ArticleDetailResponseDto {
    //의미 없는 값이 랜덤으로 생성되는 것을 막기 위해 private로 필드 변수 선언
    private Long id;
    private String title;
    private String content;
    private String author;

    //엔티티에서 값을 가져오도록
    public ArticleDetailResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.author = article.getAuthor();
    }
}
