package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findByUserId (Long id, Pageable pageable);

    Category findByUserIdAndId (Long userId,Long id);

    void deleteByIdAndUserId (Long id, Long userId);


}
