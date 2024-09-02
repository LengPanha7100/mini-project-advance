package com.kshrd.springadvanceminiproject.service;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleCommentRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleGetResponse;
import com.kshrd.springadvanceminiproject.model.entity.Article;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ArticleService {

    List<ArticleGetResponse> getAllArticle(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);

    ArticleGetResponse getByIdArticle(Long id);

    ArticleCreateResponse createArticle(ArticleRequest articleRequest);

    ArticleCreateResponse updateArticle(ArticleRequest articleRequest, Long id);

    void deleteArticle(Long id);

    ArticleCreateResponse createComment(ArticleCommentRequest articleCommentRequest, Long id);

    ArticleCreateResponse getCommentByArticleId(Long id);
}
