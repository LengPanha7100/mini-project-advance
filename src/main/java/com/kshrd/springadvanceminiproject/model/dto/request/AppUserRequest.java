package com.kshrd.springadvanceminiproject.model.dto.request;

import com.kshrd.springadvanceminiproject.model.entity.User;
import com.kshrd.springadvanceminiproject.model.enums.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {

    private String username;

    @NotBlank(message = "The email is required.")
    @Email(message = "Invalid email address.")
    private String email;

    private String address;

    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$",
            message = "Phone number should follow this format: 012-333-4321 or 012 333 4321")
    private String phoneNumber;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character.")
    @NotBlank
    private String password;

    private Role role;

    public User toEntity() {
        return new User(null, this.username, this.email, this.role, this.password, this.address, this.phoneNumber, LocalDateTime.now(), null, null, null, null, null);
    }

}
