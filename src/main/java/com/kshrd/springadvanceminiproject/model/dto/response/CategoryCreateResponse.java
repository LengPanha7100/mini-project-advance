package com.kshrd.springadvanceminiproject.model.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryCreateResponse {
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
