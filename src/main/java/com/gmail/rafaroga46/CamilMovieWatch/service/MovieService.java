package com.gmail.rafaroga46.CamilMovieWatch.service;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Movie;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.gmail.rafaroga46.CamilMovieWatch.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository,
                        CategoryService categoryService, StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return movieRepository.save(movie);

    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category ->
                categoryService.findById(category.getId()).ifPresent(categoriesFound::add));

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming ->
                streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add));

        return streamingsFound;
    }


}
