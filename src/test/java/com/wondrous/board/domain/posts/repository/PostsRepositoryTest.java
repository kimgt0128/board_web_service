package com.wondrous.board.domain.posts.repository;

import com.wondrous.board.domain.posts.eneity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired
    ArticleRepository postsRepository;

    @PersistenceContext
    EntityManager entityManager; // Inject the EntityManager

    @AfterEach
    @Rollback
    void tearDown() {
        postsRepository.deleteAll(); // Clear the repository after each test
        entityManager.flush(); // Flush changes to the database
        entityManager.clear(); // Clear the EntityManager to reset state
    }

    @Test
    @Transactional
    public void 게시글_저장_불러오기() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 내용";
        String author = "DevloperKT";
        Article expected = Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build(); // Create expected post without id

        // when
        Article actual = postsRepository.save(expected); // Save the post

        // then
        assertThat(actual).isEqualTo(expected); // Compare only the title
    }

    @Test
    @DisplayName("모든 게시글을 조회")
    @Transactional
    public void posts() {
        // given
        Article post1 = Article.builder().id(4L).title("테스트 제목1").content("테스트 내용1").author("사람1").build();
        Article post2 = Article.builder().id(5L).title("테스트 제목2").content("테스트 내용2").author("사람2").build();
        List<Article> expected = Arrays.asList(post1, post2);

        // when
        postsRepository.save(post1);
        postsRepository.save(post2);

        // then
        List<Article> actual = postsRepository.findAll();
        assertThat(actual.size()).isEqualTo(5);
    }
}