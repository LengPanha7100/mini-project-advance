package com.kshrd.springadvanceminiproject.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kshrd.springadvanceminiproject.model.dto.response.ArticleGetResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryGetByIdResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer amountOfArticles;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryArticle> categoryArticles;

    public CategoryGetByIdResponse toResponse(){
        List<ArticleGetResponse> articleGetResponses = new ArrayList<>();
        for(CategoryArticle categoryArticle : categoryArticles){
            articleGetResponses.add(categoryArticle.getArticle().toResponse());
        }
        return new CategoryGetByIdResponse(this.id,this.name, articleGetResponses.size(),this.createdAt,this.updateAt,articleGetResponses);
    }


}
