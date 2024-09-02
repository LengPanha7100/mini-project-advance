package com.kshrd.springadvanceminiproject.service.serviceimpl;

import com.kshrd.springadvanceminiproject.exception.NotFoundException;
import com.kshrd.springadvanceminiproject.model.dto.request.CommentRequest;
import com.kshrd.springadvanceminiproject.model.entity.Comment;
import com.kshrd.springadvanceminiproject.repository.CommentRepository;
import com.kshrd.springadvanceminiproject.service.AuthService;
import com.kshrd.springadvanceminiproject.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final AuthService authService;

    public CommentServiceImp(CommentRepository commentRepository, AuthService authService) {
        this.commentRepository = commentRepository;
        this.authService = authService;
    }

    @Override
    public Comment getByIdComment(Long id) {
        Long userId = authService.findCurrentUser();
        Comment comment = commentRepository.findByUserIdAndId(userId,id);
        if(comment == null){
            throw new NotFoundException("Not found");
        }
        return comment;
    }

    @Override
    public Comment updateComment(CommentRequest commentRequest, Long id) {
        Long userId = authService.findCurrentUser();
        Comment comment = commentRepository.findByUserIdAndId(userId,id);
        return commentRepository.save(commentRequest.toEntity(id));
    }

    @Override
    public void deleteComment(Long id) {
        Long userId = authService.findCurrentUser();
        commentRepository.findByUserIdAndId(userId,id);
    }
}
