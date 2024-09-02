package com.kshrd.springadvanceminiproject.controller;

import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.request.AppUserRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.AuthRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.AppUserResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.AuthResponse;
import com.kshrd.springadvanceminiproject.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auths")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login via credential to get token")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest) throws Exception {
        ApiResponse<AuthResponse> response = ApiResponse.<AuthResponse>builder()
                .message("Login successfully.")
                .status(HttpStatus.OK)
                .payload(authService.login(authRequest))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    @Operation(summary = "Register as a new user")
    public ResponseEntity<ApiResponse<AppUserResponse>> register(@Valid @RequestBody AppUserRequest appUserRequest) {
        ApiResponse<AppUserResponse> response = ApiResponse.<AppUserResponse>builder()
                .message("Registered successfully.")
                .status(HttpStatus.OK)
                .payload(authService.register(appUserRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
