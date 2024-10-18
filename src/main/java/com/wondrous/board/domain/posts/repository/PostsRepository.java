package com.wondrous.board.domain.posts.repository;

import com.wondrous.board.domain.posts.eneity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
//    @Override
//    @Lock(LockModeType.OPTIMISTIC)
//    List<Posts> findAll();
//    Posts findById(long id);
}
