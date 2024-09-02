package com.kshrd.springadvanceminiproject.controller;

import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.BookmarkResponse;
import com.kshrd.springadvanceminiproject.model.entity.Bookmark;
import com.kshrd.springadvanceminiproject.service.BookmarkService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmark")
@SecurityRequirement(name = "bearerAuth")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBookmark(@RequestParam (defaultValue = "0") Integer pageNo,
                                            @RequestParam (defaultValue = "10") Integer pageSize,
                                            @RequestParam (defaultValue = "id") String sortBy,
                                            @RequestParam Sort.Direction orderBy){
        List<BookmarkResponse> bookmarks = bookmarkService.getAllBookmark(pageNo,pageSize,sortBy,orderBy);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get all bookmark successfully")
                .status(HttpStatus.OK)
                .payload(bookmarks)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<?> createBookmarkGetArticleId (@PathVariable Long articleId){
        BookmarkResponse bookmark = bookmarkService.createBookmarkGetArticleId(articleId);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Create bookmark is successfully")
                .status(HttpStatus.CREATED)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{bookmarkId}")
    public ResponseEntity<?> updateBookmarkByBookmarkId(@PathVariable Long bookmarkId , @RequestParam Boolean status){
        BookmarkResponse bookmark = bookmarkService.updateBookmarkByBookmarkId(bookmarkId,status);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Update book mark by article id "+ bookmarkId + " is successfully")
                .status(HttpStatus.OK)
                .payload(bookmark)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
