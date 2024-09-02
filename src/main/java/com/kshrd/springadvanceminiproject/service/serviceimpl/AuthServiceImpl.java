package com.kshrd.springadvanceminiproject.service.serviceimpl;

import com.kshrd.springadvanceminiproject.exception.BadRequestException;
import com.kshrd.springadvanceminiproject.exception.NotFoundException;
import com.kshrd.springadvanceminiproject.jwt.JwtService;
import com.kshrd.springadvanceminiproject.model.dto.request.AppUserRequest;
import com.kshrd.springadvanceminiproject.model.dto.request.AuthRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.AppUserResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.AuthResponse;
import com.kshrd.springadvanceminiproject.model.entity.User;
import com.kshrd.springadvanceminiproject.repository.AppUserRepository;
import com.kshrd.springadvanceminiproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.undo.CannotUndoException;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    private void authenticate(String email, String password) {
        User user = appUserRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Invalid email"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Invalid Password");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        User user = appUserRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AppUserResponse register(AppUserRequest appUserRequest) {
        Optional<User> user = appUserRepository.findByEmail(appUserRequest.getEmail());
        if (user.isPresent()) {
            throw new BadRequestException("This email is already registered");
        }
        appUserRequest.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        return appUserRepository.save(appUserRequest.toEntity()).toResponse();
    }

    @Override
    public Long findCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    @Override
    public AppUserResponse getCurrentUser() {
        return appUserRepository.findById(findCurrentUser()).orElseThrow().toResponse();
    }

    @Override
    public AppUserResponse updateUser(AppUserRequest appUserRequest) {
        Long currentId = findCurrentUser();
        User currentUser = appUserRepository.findById(currentId).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        currentUser.setUsername(appUserRequest.getUsername());
        currentUser.setEmail(appUserRequest.getEmail());
        currentUser.setAddress(appUserRequest.getAddress());
        currentUser.setPhoneNumber(appUserRequest.getPhoneNumber());
        currentUser.setRole(appUserRequest.getRole());
        if (appUserRequest.getPassword() != null && !appUserRequest.getPassword().isEmpty()){
            currentUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        }

        User updateUser = appUserRepository.save(currentUser);
        return updateUser.toResponse();
    }


}
