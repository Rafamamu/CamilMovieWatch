package com.gmail.rafaroga46.CamilMovieWatch.repository;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {


    List<Movie> findMovieByCategories(List<Category> categories);
}
