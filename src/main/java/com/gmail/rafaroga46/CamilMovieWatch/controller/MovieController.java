package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.MovieRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.MovieResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Movie;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.MovieMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/camilmoviewatch/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request ) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));

        return  ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));

    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList());
    }
    

}
