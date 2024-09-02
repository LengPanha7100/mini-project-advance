package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment,Long> {
    Comment findByUserIdAndId (Long userid , Long id);

    void deleteByIdAndUserId (Long id , Long userId);
}
