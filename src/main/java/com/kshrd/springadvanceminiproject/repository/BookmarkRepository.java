package com.kshrd.springadvanceminiproject.repository;

import com.kshrd.springadvanceminiproject.model.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    Page<Bookmark> findByUserId (Long userId, Pageable pageable);

    Bookmark findByUserIdAndId (Long userId , Long bookmarkId);
}
