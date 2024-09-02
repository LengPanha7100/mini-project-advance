package com.kshrd.springadvanceminiproject.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kshrd.springadvanceminiproject.model.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private Long id;
    private String username;
    private String email;
    private String address;
    private String phoneNumber;
    private LocalDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updatedAt;
    private Role role;
}
