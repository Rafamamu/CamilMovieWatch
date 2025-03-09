package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camilmoviewatch/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }
    

}
