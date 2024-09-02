package com.kshrd.springadvanceminiproject.controller;

import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.request.AppUserRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.AppUserResponse;
import com.kshrd.springadvanceminiproject.model.entity.User;
import com.kshrd.springadvanceminiproject.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping
    @Operation(summary = "Get current user info")
    public ResponseEntity<?> getCurrentUser(){
        AppUserResponse userServiceList = authService.getCurrentUser();
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get current user information successfully.")
                .status(HttpStatus.OK)
                .payload(userServiceList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping
    public ResponseEntity<?> updateUser (@RequestBody AppUserRequest appUserRequest){
        AppUserResponse appUserResponse = authService.updateUser(appUserRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Updated current user information successfully!")
                .status(HttpStatus.OK)
                .payload(appUserResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
