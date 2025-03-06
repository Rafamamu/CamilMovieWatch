package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.CategoryRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.CategoryResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.CategoryMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.StreamSupport.stream;

@RestController()
@RequestMapping("/camilmoviewatch/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse>  categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        Category savedCategory = categoryService.saveCategory(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryResponse(savedCategory));

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id) {
       return categoryService.findById(id)
               .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
               .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


}
