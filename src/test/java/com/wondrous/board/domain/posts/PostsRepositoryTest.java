package com.wondrous.board.domain.posts;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

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
        Posts expected = new Posts(title, content, author); // Create expected post without id

        // when
        Posts actual = postsRepository.save(expected); // Save the post

        // then
        assertThat(actual).isEqualTo(expected); // Compare only the title
    }

    @Test
    @DisplayName("모든 게시글을 조회")
    @Transactional
    public void posts() {
        // given
        Posts post1 = new Posts("테스트 게시글1", "테스트 내용1", "DevloperKT");
        Posts post2 = new Posts("테스트 게시글2", "테스트 내용2", "DevloperGT");
        List<Posts> expected = Arrays.asList(post1, post2);

        // when
        postsRepository.save(post1);
        postsRepository.save(post2);

        // then
        List<Posts> actual = postsRepository.findAll();
        assertThat(actual.toString()).isEqualTo(expected.toString());
    }
}