package com.kshrd.springadvanceminiproject.controller;

import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.request.CommentRequest;
import com.kshrd.springadvanceminiproject.model.entity.Comment;
import com.kshrd.springadvanceminiproject.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getByIdComment(@PathVariable Long id){
        Comment comment = commentService.getByIdComment(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get by id comment is successfully")
                .status(HttpStatus.OK)
                .payload(comment)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@RequestBody CommentRequest commentRequest,@PathVariable Long id){
        Comment comment = commentService.updateComment(commentRequest,id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Updated comment is successfully")
                .status(HttpStatus.OK)
                .payload(comment)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteComment (@PathVariable Long id){
        commentService.deleteComment(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Deleted commit is successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
