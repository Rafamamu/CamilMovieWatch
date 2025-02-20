package com.gmail.rafaroga46.CamilMovieWatch.repository;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long> {

}
