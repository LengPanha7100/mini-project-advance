package com.kshrd.springadvanceminiproject.service;

import com.kshrd.springadvanceminiproject.model.dto.response.BookmarkResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BookmarkService {


    BookmarkResponse createBookmarkGetArticleId(Long articleId);

    BookmarkResponse updateBookmarkByBookmarkId(Long bookmarkId, Boolean status);

    List<BookmarkResponse> getAllBookmark(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);
}
