package com.kshrd.springadvanceminiproject.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookmarkResponse {
    private Long id;
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AppUserResponse user;


}
