package com.gmail.rafaroga46.CamilMovieWatch.service;

import com.gmail.rafaroga46.CamilMovieWatch.controller.response.MovieResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Movie;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.gmail.rafaroga46.CamilMovieWatch.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public  Optional<Movie> findById(Long id) {
         return movieRepository.findById(id);
    }

    public Optional<Movie> update(Long movieId, Movie updateMovie) {

        List<Category> categories = this.findCategories(updateMovie.getCategories());
        List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

        Optional<Movie> optMovie = movieRepository.findById(movieId);
        if (optMovie.isPresent()) {

            Movie movie = optMovie.get();
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setReleaseDate(updateMovie.getReleaseDate());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);


        }

        return Optional.empty();
    }

    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
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
