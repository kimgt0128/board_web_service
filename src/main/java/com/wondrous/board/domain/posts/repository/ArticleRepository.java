package com.wondrous.board.domain.posts.repository;

import com.wondrous.board.domain.posts.eneity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
//    @Override
//    @Lock(LockModeType.OPTIMISTIC)
//    List<Posts> findAll();
//    Posts findById(long id);
}
