package com.wondrous.board.domain.posts.dto.response;

import com.wondrous.board.domain.posts.eneity.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    //의미 없는 값이 랜덤으로 생성되는 것을 막기 위해 private로 필드 변수 선언
    private Long id;
    private String title;
    private String content;
    private String author;

    //엔티티에서 값을 가져오도록
    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    @Builder
    public PostsResponseDto(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
