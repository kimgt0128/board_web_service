package com.wondrous.board.domain.posts.controller;

import com.wondrous.board.domain.posts.dto.request.PostsSaveRequestDto;
import com.wondrous.board.domain.posts.dto.request.PostsUpdateRequestDto;
import com.wondrous.board.domain.posts.dto.response.PostsResponseDto;
import com.wondrous.board.domain.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private PostsService postsService;

    //post 객체의 id를 Http 요청으로 받고 DB에서 조회하는 메서드
    @GetMapping("/api/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    //모든 post 객체들을 조회하는 메서드
    @GetMapping("/api/posts")
    public List<PostsResponseDto> index() {
        List<PostsResponseDto> posts = postsService.findAll();
        return posts;
    }

    //post 객체를 Http 요청으로 불러와서 DB에 저장하는 메서드
    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //post 객체의 id를 Http 요청으로 불러와서 수정하고 저장해주는 메서드
    @PostMapping("/api/posts/update/{id}")
    public PostsResponseDto update(@PathVariable Long id,
                        @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //삭제
    @PostMapping("/api/posts/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return postsService.delete(id);
    }
}
