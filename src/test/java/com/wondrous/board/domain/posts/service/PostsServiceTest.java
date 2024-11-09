package com.wondrous.board.domain.posts.service;

import com.wondrous.board.domain.posts.dto.response.PostDetailResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsServiceTest {
    @Autowired
    private ArticleService postsService;

    @Test
    void save() {

    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //예상
        PostDetailResponseDto postsResponseDto1 = PostDetailResponseDto.builder().id(1L).title("삼성바이오로직스").content("100주 매수").author("홍길동").build();
        PostDetailResponseDto postsResponseDto2 = PostDetailResponseDto.builder().id(2L).title("엔비디아").content("200주 매수").author("김길동").build();
        PostDetailResponseDto postsResponseDto3 = PostDetailResponseDto.builder().id(3L).title("구글").content("300주 매수").author("박길동").build();
        List<PostDetailResponseDto> expected = List.of(postsResponseDto1, postsResponseDto2, postsResponseDto3);

        //실제
        List<PostDetailResponseDto> actual = postsService.findAll();


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