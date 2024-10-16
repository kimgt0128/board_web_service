package com.wondrous.board.domain.posts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j

@RestController
public class PostsApiController {
    @Autowired
    PostsRepository postsRepository;

    //post 객체의 id를 Http 요청으로 받고 DB에서 조회하는 메서드
    @GetMapping("/api/posts/{id}")
    public Posts findById(@PathVariable Long id) {
        Posts postEntity =  postsRepository.findById(id).orElse(null);
        return postEntity;
    }

    //모든 post 객체들을 조회하는 메서드
    @GetMapping("/api/posts")
    public List<Posts> index() {
        List<Posts> index = postsRepository.findAll();
        log.info(index.toString());
        return index;
    }

    //post 객체를 Http 요청으로 불러와서 DB에 저장하는 메서드
    @PostMapping("/api/posts")
    public Long save(@RequestBody Posts posts) {
        return postsRepository.save(posts).getId();
    }

    //post 객체의 id를 Http 요청으로 불러와서 수정하고 저장해주는 메서드
    @PostMapping("/api/posts/update/{id}")
    public Posts update(@PathVariable Long id,
                        @RequestBody Posts posts) {
        //findById로 조회하고
        Posts postEntity = postsRepository.findById(id).orElse(null);

        //URL에서 가져온 제목과 내용으로 업데이트하고
        String newTitle = posts.getTitle();

        String newContent = posts.getContent();
        postEntity.update(newTitle, newContent);

        //수정한 post 객체를 DB에 저장
        return postsRepository.save(postEntity);

    }

    //삭제
    @PostMapping("/api/posts/delete/{id}")
    public void delete(@PathVariable Long id) {
        postsRepository.deleteById(id);
    }
}
