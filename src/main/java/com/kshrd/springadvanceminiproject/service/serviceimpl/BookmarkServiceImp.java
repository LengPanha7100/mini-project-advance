package com.kshrd.springadvanceminiproject.service.serviceimpl;

import com.kshrd.springadvanceminiproject.exception.NotFoundException;
import com.kshrd.springadvanceminiproject.model.dto.response.AppUserResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.BookmarkResponse;
import com.kshrd.springadvanceminiproject.model.entity.Article;
import com.kshrd.springadvanceminiproject.model.entity.Bookmark;
import com.kshrd.springadvanceminiproject.repository.AppUserRepository;
import com.kshrd.springadvanceminiproject.repository.ArticleRepository;
import com.kshrd.springadvanceminiproject.repository.BookmarkRepository;
import com.kshrd.springadvanceminiproject.service.AuthService;
import com.kshrd.springadvanceminiproject.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookmarkServiceImp implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final AuthService authService;
    private final AppUserRepository appUserRepository;
    private final ArticleRepository articleRepository;

    public BookmarkServiceImp(BookmarkRepository bookmarkRepository, AuthService authService, AppUserRepository appUserRepository, ArticleRepository articleRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.authService = authService;
        this.appUserRepository = appUserRepository;
        this.articleRepository = articleRepository;
    }



    @Override
    public BookmarkResponse createBookmarkGetArticleId(Long articleId) {
        Long userId = authService.findCurrentUser();
        Article article = articleRepository.findByUserIdAndId(userId,articleId);
        if(article == null){
            throw new NotFoundException("Not found!");
        }
        Bookmark bookmark = new Bookmark();
        bookmark.setCreatedAt(LocalDateTime.now());
        bookmark.setUser(appUserRepository.findById(userId).orElseThrow());
        bookmark.setStatus(true);
        bookmark.setArticle(article);
        Bookmark bookmark1 = bookmarkRepository.save(bookmark);
        return new BookmarkResponse(bookmark1.getId(),bookmark1.getStatus(),bookmark1.getCreatedAt(),bookmark1.getUpdateAt(),bookmark1.getUser().toResponse());
    }

    @Override
    public BookmarkResponse updateBookmarkByBookmarkId(Long bookmarkId, Boolean status) {
        Long userId = authService.findCurrentUser();
        Bookmark bookmark = bookmarkRepository.findByUserIdAndId(userId,bookmarkId);
        if(bookmark == null){
            throw new NotFoundException("Not found!");
        }
        bookmark.setStatus(status);
        bookmark.setUpdateAt(LocalDateTime.now());
        Bookmark bookmark1 = bookmarkRepository.save(bookmark);
        return bookmark1.toResponse() ;
    }

    @Override
    public List<BookmarkResponse> getAllBookmark(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Long userId = authService.findCurrentUser();
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Bookmark> bookmarks = bookmarkRepository.findByUserId(userId,pageable);
        List<BookmarkResponse> bookmarkResponses = new ArrayList<>();
        for(Bookmark bookmark : bookmarks.getContent()){
            bookmarkResponses.add(bookmark.toResponse());
        }
        return bookmarkResponses;
    }
}
