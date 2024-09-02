package com.kshrd.springadvanceminiproject.service;

import com.kshrd.springadvanceminiproject.model.dto.request.CommentRequest;
import com.kshrd.springadvanceminiproject.model.entity.Comment;

public interface CommentService {
    Comment getByIdComment(Long id);

    Comment updateComment(CommentRequest commentRequest, Long id);

    void deleteComment(Long id);
}
