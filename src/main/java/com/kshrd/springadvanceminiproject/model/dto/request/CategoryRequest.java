package com.kshrd.springadvanceminiproject.model.dto.request;

import com.kshrd.springadvanceminiproject.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String categoryName;
    private Integer amountOfArticles;
    private LocalDateTime createdAt;

    public Category toEntity(){
        return new Category(null,this.categoryName,0,this.createdAt,null,null,null);
    }
    public Category toEntity(Long id){
        return new Category(id,this.categoryName,0,this.createdAt,LocalDateTime.now(),null,null);
    }


}
