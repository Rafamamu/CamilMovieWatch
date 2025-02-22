package com.gmail.rafaroga46.CamilMovieWatch.service;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
         return  categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);

    }
}
