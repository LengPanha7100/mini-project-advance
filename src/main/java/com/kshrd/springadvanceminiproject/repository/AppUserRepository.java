package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

}
