package com.kshrd.springadvanceminiproject.service;

import com.kshrd.springadvanceminiproject.model.dto.request.CategoryRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryGetByIdResponse;
import com.kshrd.springadvanceminiproject.model.entity.Category;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    List<CategoryGetByIdResponse> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);

    CategoryGetByIdResponse getByIdCategory(Long id);

    CategoryCreateResponse createCategory(CategoryRequest categoryRequest);

    CategoryCreateResponse updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);
}
