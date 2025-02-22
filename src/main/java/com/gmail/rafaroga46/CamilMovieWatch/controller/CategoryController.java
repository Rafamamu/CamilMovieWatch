package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/camilmoviewatch/category")
public class CategoryController {



    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAll();

    }

    @PostMapping()
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);

    }




}
