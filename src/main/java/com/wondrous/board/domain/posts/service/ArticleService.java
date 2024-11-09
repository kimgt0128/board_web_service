package com.wondrous.board.domain.posts.service;

import com.wondrous.board.domain.posts.dto.request.ArticleSaveRequestDto;
import com.wondrous.board.domain.posts.dto.request.ArticleUpdateRequestDto;
import com.wondrous.board.domain.posts.dto.response.ArticleDetailResponseDto;
import com.wondrous.board.domain.posts.dto.response.SearchedArticleSimpleResponseDto;
import com.wondrous.board.domain.posts.dto.response.SearchedArticlesSimpleResponseDto;
import com.wondrous.board.domain.posts.eneity.Article;
import com.wondrous.board.domain.posts.exception.ArticleNotFoundException;
import com.wondrous.board.domain.posts.repository.ArticleRepository;
import com.wondrous.board.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    /**생성자 주입으로 레포지토리 의존성 추가(권장)
    //서비스 계층에서 Dto Entity 책임 분리!
    Exctption Haldling - excepion에서 정의한 사용자 정의 에러 코드 적용
    **/

@RequiredArgsConstructor

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    //게시글 생성 메서드(Create)
    @Transactional
    public ArticleDetailResponseDto saveArticle(ArticleSaveRequestDto request) {
        Article articleEntity = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(request.getAuthor()).build();

        Article saved = articleRepository.save(articleEntity);
        return new ArticleDetailResponseDto(saved);
    }

    //게시글 조회 메서드(Read)
    public ArticleDetailResponseDto findArticleById(Long articleId) {
        Article searched = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        return new ArticleDetailResponseDto(searched);
    }

    //모든 게시글 조회 메서드(Read)
    public SearchedArticlesSimpleResponseDto findAllArticle() {
        List<Article> entities = articleRepository.findAll();
        //stream 문법으로 간단한 for문 해결
        List<SearchedArticleSimpleResponseDto> searchedDtos = entities.stream()
                .map(SearchedArticleSimpleResponseDto::new)
                .toList();
        return new SearchedArticlesSimpleResponseDto(searchedDtos);
    }

    @Transactional
    //게시글 수정 메서드(Update)
    public ArticleDetailResponseDto updateArticle(Long articleId, ArticleUpdateRequestDto request) {
        Article targetArticle = articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        targetArticle.update(request.getTitle(), request.getContent()); //단순 데이터 변경이므로 멱등성 보장
        Article updated = articleRepository.save(targetArticle);
        return new ArticleDetailResponseDto(updated);
    }

    @Transactional
    //게시글 삭제 메서드(Delete)
    public void deleteArticle(Long articleID) {
        //만약 해당 id의 게시글이 데이터베이스에 없을 경우 예외 처리
        Article targetArticle = articleRepository.findById(articleID).orElseThrow(
                () -> new ArticleNotFoundException(ErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.delete(targetArticle);
    }

}
