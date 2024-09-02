package com.kshrd.springadvanceminiproject.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryGetByIdResponse {
    private Long categoryId;;
    private String categoryName;
    private Integer amountOfArticles;
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ArticleGetResponse> articleList;
}
