package com.kshrd.springadvanceminiproject.model.dto.request;

import com.kshrd.springadvanceminiproject.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    public Comment toEntity(Long id){
        return new Comment(id,this.content,null,LocalDateTime.now(),null,null);
    }
}
