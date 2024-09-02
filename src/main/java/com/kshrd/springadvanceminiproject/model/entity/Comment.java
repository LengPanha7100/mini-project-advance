package com.kshrd.springadvanceminiproject.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kshrd.springadvanceminiproject.model.dto.response.CommentGetResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public CommentGetResponse toResponse (){
        return new CommentGetResponse(this.id,this.content,this.createdAt,this.updateAt,this.user.toResponse());
    }
}
