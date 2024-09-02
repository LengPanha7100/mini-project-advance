package com.kshrd.springadvanceminiproject.controller;

import com.kshrd.springadvanceminiproject.model.apiresponse.ApiResponse;
import com.kshrd.springadvanceminiproject.model.dto.request.CategoryRequest;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryCreateResponse;
import com.kshrd.springadvanceminiproject.model.dto.response.CategoryGetByIdResponse;
import com.kshrd.springadvanceminiproject.model.entity.Category;
import com.kshrd.springadvanceminiproject.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory (@RequestParam (defaultValue = "1") Integer pageNo,
                                             @RequestParam (defaultValue = "10") Integer pageSize,
                                             @RequestParam (defaultValue = "id") String sortBy,
                                             @RequestParam Sort.Direction orderBy){
        List<CategoryGetByIdResponse> categories = categoryService.getAllCategory(pageNo,pageSize,sortBy,orderBy);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get all category is successfully")
                .status(HttpStatus.OK)
                .payload(categories)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdCategory (@PathVariable Long id){
        CategoryGetByIdResponse category = categoryService.getByIdCategory(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Get by id category is successfully")
                .status(HttpStatus.OK)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){
        CategoryCreateResponse category = categoryService.createCategory(categoryRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Created category is successfully")
                .status(HttpStatus.CREATED)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        CategoryCreateResponse category = categoryService.updateCategory(id,categoryRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Updated category is successfully")
                .status(HttpStatus.OK)
                .payload(category)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .message("Deleted category is successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
