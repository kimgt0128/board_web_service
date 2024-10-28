package com.wondrous.board.domain.posts.service;

import com.wondrous.board.domain.posts.dto.request.PostsSaveRequestDto;
import com.wondrous.board.domain.posts.dto.response.PostsResponseDto;
import com.wondrous.board.domain.posts.eneity.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsServiceTest {
    @Autowired
    private PostsService postsService;

    @Test
    void save() {

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //예상
        PostsResponseDto postsResponseDto1 = PostsResponseDto.builder().id(1L).title("삼성바이오로직스").content("100주 매수").author("홍길동").build();
        PostsResponseDto postsResponseDto2 = PostsResponseDto.builder().id(2L).title("엔비디아").content("200주 매수").author("김길동").build();
        PostsResponseDto postsResponseDto3 = PostsResponseDto.builder().id(3L).title("구글").content("300주 매수").author("박길동").build();
        List<PostsResponseDto> expected = List.of(postsResponseDto1, postsResponseDto2, postsResponseDto3);

        //실제
        List<PostsResponseDto> actual = postsService.findAll();


        //실제: toString사용하지 않고 객체 필드 하나하나 가져와서 비교하거나 특정 값이 있는지만 체크!
        assertThat(actual).filteredOn(posts -> posts.getTitle().contains("엔비디아"))
                .containsOnly(expected.get(1));

        }



    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}