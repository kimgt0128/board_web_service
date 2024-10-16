package com.wondrous.board.domain.posts;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
//    @Override
//    @Lock(LockModeType.OPTIMISTIC)
//    List<Posts> findAll();
//    Posts findById(long id);
}
