package com.kshrd.springadvanceminiproject.model.entity;

import com.kshrd.springadvanceminiproject.model.dto.response.BookmarkResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bookmarks")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public BookmarkResponse toResponse(){
        return new BookmarkResponse(this.id,this.status,this.createdAt,this.updateAt,this.user.toResponse());
    }
}
