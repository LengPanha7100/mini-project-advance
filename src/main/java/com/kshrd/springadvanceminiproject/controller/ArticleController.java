package com.kshrd.springadvanceminiproject.controller;
import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleCommentRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.ArticleRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleGetResponse;
import com.kshrd.springadvanceminiproject.model.entity.Article;
import com.kshrd.springadvanceminiproject.model.entity.Comment;
import com.kshrd.springadvanceminiproject.service.ArticleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
@SecurityRequirement(name = "bearerAuth")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> getAllArticle (@RequestParam (defaultValue = "0") Integer pageNo,
                                            @RequestParam (defaultValue = "10") Integer pageSize,
                                            @RequestParam (defaultValue = "id") String sortBy,
                                            @RequestParam Sort.Direction orderBy){
        List<ArticleGetResponse> articles = articleService.getAllArticle(pageNo,pageSize,sortBy,orderBy);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get all article is successfully")
                .status(HttpStatus.OK)
                .payload(articles)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdArticle (@PathVariable Long id){
        ArticleGetResponse article = articleService.getByIdArticle(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get by id article is successfully")
                .status(HttpStatus.OK)
                .payload(article)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createArticle (@RequestBody ArticleRequest articleRequest){
        ArticleCreateResponse article1 = articleService.createArticle(articleRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Created article is successfully")
                .status(HttpStatus.CREATED)
                .payload(article1)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle (@RequestBody ArticleRequest articleRequest , @PathVariable Long id){
        ArticleCreateResponse article = articleService.updateArticle(articleRequest,id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Updated article is successfully")
                .status(HttpStatus.OK)
                .payload(article)
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle (@PathVariable Long id){
        articleService.deleteArticle(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Deleted article is successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> createComment(@RequestBody ArticleCommentRequest articleCommentRequest, @PathVariable Long id){
        ArticleCreateResponse articleCreateResponse = articleService.createComment(articleCommentRequest,id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Created comment successfully")
                .status(HttpStatus.OK)
                .payload(articleCreateResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentByArticleId(@PathVariable Long id){
        ArticleCreateResponse articleCreateResponse = articleService.getCommentByArticleId(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get comment by article id successfully")
                .status(HttpStatus.OK)
                .payload(articleCreateResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
