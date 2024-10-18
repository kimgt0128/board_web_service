package com.wondrous.board.domain.posts.service;

import com.wondrous.board.domain.posts.dto.request.PostsSaveRequestDto;
import com.wondrous.board.domain.posts.dto.request.PostsUpdateRequestDto;
import com.wondrous.board.domain.posts.dto.response.PostsResponseDto;
import com.wondrous.board.domain.posts.eneity.Posts;
import com.wondrous.board.domain.posts.repository.PostsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    //생성자 주입으로 레포지토리 의존성 추가(권장)
    //서비스 계층에서 Dto Entity 책임 분리!

    private PostsRepository postsRepository;
    //게시글 생성 메서드(Create)
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        return postsRepository.save(posts).getId();
    }

    //게시글 조회 메서드(Read)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElse(null);
        return new PostsResponseDto(entity);
    }

    //모든 게시글 조회 메서드(Read)
    public List<PostsResponseDto> findAll() {
        List<Posts> entities = postsRepository.findAll();
        List<PostsResponseDto> dtos = new ArrayList<>();
        //stream 문법으로 간단한 for문 해결
        entities.forEach(entity -> dtos.add(new PostsResponseDto(entity)));
        return dtos;
    }

    @Transactional
    //게시글 수정 메서드(Update)
    public PostsResponseDto update(Long id, PostsUpdateRequestDto requestDto) {
        Posts entity = postsRepository.findById(id).orElse(null);
        entity.update(requestDto.getTitle(), requestDto.getContent());
        Posts updated = postsRepository.save(entity);
        return new PostsResponseDto(updated);
    }

    @Transactional
    //게시글 삭제 메서드(Delete)
    public Long delete(Long id) {
        //만약 해당 id의 게시글이 데이터베이스에 없을 경우 예외 처리
        Posts target = postsRepository.findById(id).orElse(null);
        postsRepository.delete(target);
        return target.getId();
    }

}
