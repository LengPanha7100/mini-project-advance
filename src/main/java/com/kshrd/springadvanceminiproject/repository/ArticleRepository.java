package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ArticleRepository extends JpaRepository<Article,Long> {

    Page<Article> findByUserId (Long id , Pageable pageable);

    Article findByUserIdAndId (Long userId,Long id);

    void deleteByIdAndUserId (Long id,Long userId);
}
