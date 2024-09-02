package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
