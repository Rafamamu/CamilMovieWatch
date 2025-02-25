package com.gmail.rafaroga46.CamilMovieWatch.mapper;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.CategoryRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.CategoryResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
                .builder()
                .nome(categoryRequest.nome())
                .build();




    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
                .builder()
                .id(category.getId())
                .nome(category.getNome())
                .build();

    }


}
