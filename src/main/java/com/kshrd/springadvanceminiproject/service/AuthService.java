package com.kshrd.springadvanceminiproject.service;


import com.kshrd.springadvanceminiproject.model.dto.request.AppUserRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.AuthRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.AppUserResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.AuthResponse;
import com.kshrd.springadvanceminiproject.model.entity.User;


public interface AuthService {
    AuthResponse login(AuthRequest authRequest) throws Exception;

    AppUserResponse register(AppUserRequest appUserRequest);

    Long findCurrentUser();

    AppUserResponse getCurrentUser();

    AppUserResponse updateUser(AppUserRequest appUserRequest);
}
