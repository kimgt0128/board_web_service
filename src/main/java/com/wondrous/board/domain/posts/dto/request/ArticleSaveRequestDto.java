package com.wondrous.board.domain.posts.dto.request;

import com.wondrous.board.domain.posts.eneity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
