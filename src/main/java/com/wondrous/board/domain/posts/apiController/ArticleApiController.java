package com.wondrous.board.domain.posts.apiController;

import com.wondrous.board.domain.posts.dto.request.ArticleSaveRequestDto;
import com.wondrous.board.domain.posts.dto.request.ArticleUpdateRequestDto;
import com.wondrous.board.domain.posts.dto.response.ArticleDetailResponseDto;
import com.wondrous.board.domain.posts.dto.response.PostDetailResponseDto;
import com.wondrous.board.domain.posts.dto.response.SearchedArticlesSimpleResponseDto;
import com.wondrous.board.domain.posts.service.ArticleService;
import com.wondrous.board.global.response.DataResponse;
import com.wondrous.board.global.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/api/post")
@RestController
public class ArticleApiController {
    private final ArticleService postsService;
    private final ArticleService articleService;

    //post 객체의 id를 Http 요청으로 받고 DB에서 조회하는 메서드
    @GetMapping("/{articleId}")
    public ResponseEntity<DataResponse<ArticleDetailResponseDto>> getArticleById(@PathVariable Long articleId) {
        ArticleDetailResponseDto response = postsService.findArticleById(articleId);
        return new ResponseEntity<>(
                DataResponse.of(
                        HttpStatus.FOUND, "게시글을 정상적으로 조회했습니다.", response), HttpStatus.FOUND);
    }

    //제목 또는 내용으로 검색하는 기능 추가 예정

    //모든 article 객체들을 조회하는 메서드
    @GetMapping
    public ResponseEntity<DataResponse<SearchedArticlesSimpleResponseDto>> getAllArticles() {
        SearchedArticlesSimpleResponseDto response = postsService.findAllArticle();
        return new ResponseEntity<>(
                DataResponse.of(
                        HttpStatus.FOUND, "게시글 목록을 정상적으로 조회했습니다.", response), HttpStatus.FOUND);
    }

    //post 객체를 Http 요청으로 불러와서 DB에 저장하는 메서드
    @PostMapping
    public ResponseEntity<DataResponse<ArticleDetailResponseDto>> saveArticle(@RequestBody ArticleSaveRequestDto request) {
        ArticleDetailResponseDto response = postsService.saveArticle(request);
        return new ResponseEntity<>(
                DataResponse.of(
                        HttpStatus.OK, "게시글을 성공적으로 저장했습니다.", response), HttpStatus.OK);
    }

    //post 객체의 id를 Http 요청으로 불러와서 수정하고 저장해주는 메서드
    //단순히 지정된 값으로 데이터만 변경하므로 멱등성 보장, 따라서 PATCH 사용
    @PatchMapping("/{articleId}")
    public ResponseEntity<DataResponse<ArticleDetailResponseDto>> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleUpdateRequestDto request
    ) {
        ArticleDetailResponseDto response = postsService.updateArticle(articleId, request);
        return new ResponseEntity<>(
                DataResponse.of(
                        HttpStatus.OK, "게시글을 성공적으로 수정했습니다.", response), HttpStatus.OK);
    }

    //삭제
    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePost(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(
                MessageResponse.of(HttpStatus.OK, "게시글이 정상적으로 삭제되었습니다."), HttpStatus.OK);
    }
}
