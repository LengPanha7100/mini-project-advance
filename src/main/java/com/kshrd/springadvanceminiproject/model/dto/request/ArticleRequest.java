package com.kshrd.springadvanceminiproject.model.dto.request;

import com.kshrd.springadvanceminiproject.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequest {
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private List<Long> categoryArticles;

    public Article toEntity(){
        return new Article(null,this.title,this.description,this.createdAt,null,null,null,null,null);
    }
    public Article toEntity(Long id){
        return new Article(id,this.title,this.description,LocalDateTime.now());
    }
}
