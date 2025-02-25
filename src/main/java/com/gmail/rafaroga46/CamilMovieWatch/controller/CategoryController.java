package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.CategoryRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.CategoryResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.CategoryMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/camilmoviewatch/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

    }

    @PostMapping()
    public CategoryResponse saveCategory(@RequestBody CategoryRequest request) {
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return CategoryMapper.toCategoryResponse(savedCategory);

    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId(@PathVariable Long id) {
        Optional<Category> optCategory = categoryService.findById(id);
        if (optCategory.isPresent()) {
            return CategoryMapper.toCategoryResponse(optCategory.get());
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);


    }


}
