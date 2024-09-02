package com.kshrd.springadvanceminiproject.service.serviceimpl;
import com.kshrd.springadvanceminiproject.exception.NotFoundException;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleCommentRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleGetResponse;
import com.kshrd.springadvanceminiproject.model.entity.Article;
import com.kshrd.springadvanceminiproject.model.entity.Category;
import com.kshrd.springadvanceminiproject.model.entity.CategoryArticle;
import com.kshrd.springadvanceminiproject.model.entity.Comment;
import com.kshrd.springadvanceminiproject.repository.AppUserRepository;
import com.kshrd.springadvanceminiproject.repository.ArticleRepository;
import com.kshrd.springadvanceminiproject.repository.CategoryRepository;
import com.kshrd.springadvanceminiproject.repository.CommentRepository;
import com.kshrd.springadvanceminiproject.service.ArticleService;
import com.kshrd.springadvanceminiproject.service.AuthService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthService authService;
    private final AppUserRepository appUserRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;

    public ArticleServiceImp(ArticleRepository articleRepository, AuthService authService, AppUserRepository appUserRepository, CategoryRepository categoryRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.authService = authService;
        this.appUserRepository = appUserRepository;
        this.categoryRepository = categoryRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public List<ArticleGetResponse> getAllArticle(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Long userId = authService.findCurrentUser();
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Article> articles = articleRepository.findByUserId(userId,pageable);
        List<ArticleGetResponse> articleGetResponses = new ArrayList<>();
        for(Article article : articles.getContent()){
            articleGetResponses.add(article.toResponse());
        }
        return articleGetResponses;
    }

    @Override
    public ArticleGetResponse getByIdArticle(Long id) {
        Long userId = authService.findCurrentUser();
        Article article = articleRepository.findByUserIdAndId(userId,id);
        if (article == null){
            throw new NotFoundException("Not found!");
        }
        return article.toResponse();
    }

    @Override
    public ArticleCreateResponse createArticle(ArticleRequest articleRequest) {
        Long userId = authService.findCurrentUser();
        List<CategoryArticle> categoryArticle = new ArrayList<>();
        //set category to categoryArticle
        for(Long categoryId : articleRequest.getCategoryArticles()){
            CategoryArticle categoryArticle1 = new CategoryArticle();
            categoryArticle1.setCreatedAt(LocalDateTime.now());
            categoryArticle1.setCategory(categoryRepository.findByUserIdAndId(userId,categoryId));
            categoryArticle.add(categoryArticle1);
        }
        Article article = articleRequest.toEntity();
        article.setCreatedAt(LocalDateTime.now());
        article.setUser(appUserRepository.findById(userId).orElseThrow());
        //loop set article to categoryArticle
        categoryArticle.forEach(ca -> ca.setArticle(article));
        article.setCategoryArticles(categoryArticle);
        Article article1 = articleRepository.save(article);
        return new ArticleCreateResponse(article1.getId(),article1.getTitle(),article1.getDescription(),article1.getCreatedAt(),null,article1.getUser().getId(),null,null) ;
    }

    @Override
    public ArticleCreateResponse updateArticle(ArticleRequest articleRequest, Long id) {
        Long userId = authService.findCurrentUser();
        Article article = articleRepository.findByUserIdAndId(userId,id);
        List<CategoryArticle> categoryArticles = new ArrayList<>();
        for (Long category : articleRequest.getCategoryArticles()){
            CategoryArticle categoryArticle = new CategoryArticle();
            categoryArticle.setUpdateAt(LocalDateTime.now());
            categoryArticle.setCategory(categoryRepository.findByUserIdAndId(userId,id));
            categoryArticles.add(categoryArticle);
        }
        Article article1 = articleRequest.toEntity(id);
        categoryArticles.forEach(ca -> ca.setArticle(article1));
        Article article2 = articleRepository.save(article1);
        return new ArticleCreateResponse(article2.getId(),article2.getTitle(),article2.getDescription(),article2.getCreatedAt(),article2.getUpdateAt(),userId,null,null);
    }

    @Override
    public void deleteArticle(Long id) {
        Long userId = authService.findCurrentUser();
       articleRepository.deleteByIdAndUserId(id,userId);
    }

    @Override
    public ArticleCreateResponse createComment(ArticleCommentRequest articleCommentRequest, Long id) {
        Long userId = authService.findCurrentUser();
        Article article = articleRepository.findByUserIdAndId(userId,id);
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setUser(appUserRepository.findById(userId).orElseThrow());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setContent(articleCommentRequest.getComment());
        Comment comment1 = commentRepository.save(comment);
        List<Long> categoryId = new ArrayList<>();
        for(CategoryArticle categoryArticle : article.getCategoryArticles()){
            categoryId.add(categoryArticle.getCategory().getId());
        }
        return new ArticleCreateResponse(article.getId(),article.getTitle(),article.getDescription(),article.getCreatedAt(),null,userId,categoryId,article.getComments().stream().map(Comment::toResponse).toList());
    }

    @Override
    public ArticleCreateResponse getCommentByArticleId(Long id) {
        Long userId = authService.findCurrentUser();
        Article article = articleRepository.findByUserIdAndId(userId,id);
        if(article == null){
            throw new NotFoundException("Get comment by article id "+ id + " is successfully");
        }
        List<Long> categoryId = new ArrayList<>();
        for(CategoryArticle categoryArticle : article.getCategoryArticles()){
            categoryId.add(categoryArticle.getCategory().getId());
        }
        return new ArticleCreateResponse(article.getId(),article.getTitle(),article.getDescription(),article.getCreatedAt(),null,userId,categoryId,article.getComments().stream().map(Comment::toResponse).toList());
    }
}
