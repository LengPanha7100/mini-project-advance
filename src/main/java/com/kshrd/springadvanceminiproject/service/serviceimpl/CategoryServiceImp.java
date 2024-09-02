package com.kshrd.springadvanceminiproject.service.serviceimpl;

import com.kshrd.springadvanceminiproject.exception.NotFoundException;
import com.kshrd.springadvanceminiproject.model.dto.request.CategoryRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryGetByIdResponse;
import com.kshrd.springadvanceminiproject.model.entity.Category;
import com.kshrd.springadvanceminiproject.model.entity.User;
import com.kshrd.springadvanceminiproject.repository.AppUserRepository;
import com.kshrd.springadvanceminiproject.repository.CategoryRepository;
import com.kshrd.springadvanceminiproject.service.AuthService;
import com.kshrd.springadvanceminiproject.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final AuthService authService;
    private final AppUserRepository appUserRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository, AppUserRepository appUserRepository, AuthService authService, AppUserRepository appUserRepository1) {
        this.categoryRepository = categoryRepository;
        this.authService = authService;
        this.appUserRepository = appUserRepository1;
    }
    @Override
    public List<CategoryGetByIdResponse> getAllCategory(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Long userId = authService.findCurrentUser();
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Category> categories = categoryRepository.findByUserId(userId,pageable);
        List<CategoryGetByIdResponse> categoryGetByIdResponses = new ArrayList<>();
        for(Category category : categories.getContent()){
            categoryGetByIdResponses.add(category.toResponse());
        }
        return categoryGetByIdResponses;
    }
    @Override
    public CategoryGetByIdResponse getByIdCategory(Long id) {
        Long userId = authService.findCurrentUser();
         Category category = categoryRepository.findByUserIdAndId(userId,id);
        if (category == null){
            throw new NotFoundException("Category id" + id+" not found!");
        }
        return category.toResponse();
    }

    @Override
    public CategoryCreateResponse createCategory(CategoryRequest categoryRequest) {
        Long userId = authService.findCurrentUser();
        Category category = categoryRequest.toEntity();
        category.setCreatedAt(LocalDateTime.now());
        category.setUser(appUserRepository.findById(userId).orElseThrow());
        Category category1 = categoryRepository.save(category);
        return new CategoryCreateResponse(category1.getId(),category1.getName(),category1.getCreatedAt(),null);
    }

    @Override
    public CategoryCreateResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category categoryById = categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Not found!")
        );
        Long userId = authService.findCurrentUser();
        Category category = categoryRequest.toEntity(id);
        category.setUser(appUserRepository.findById(userId).orElseThrow());
        Category category1 = categoryRepository.save(category);
        return new CategoryCreateResponse(category1.getId(),category1.getName(),category1.getCreatedAt(),category1.getUpdateAt());
    }

    @Override
    public void deleteCategory(Long id) {
        Long userId = authService.findCurrentUser();
        categoryRepository.deleteByIdAndUserId(id,userId);
    }
}
