package com.kshrd.springadvanceminiproject.model.entity;

import com.kshrd.springadvanceminiproject.model.dto.response.ArticleGetResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "article")
    private List<Bookmark> bookmarks;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
    private List<CategoryArticle> categoryArticles;

    public ArticleGetResponse toResponse(){
        return new ArticleGetResponse(this.id,this.title,this.description,this.createdAt,this.updateAt);
    }

    public Article(Long id, String title, String description, LocalDateTime updateAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.updateAt = updateAt;
    }
}
