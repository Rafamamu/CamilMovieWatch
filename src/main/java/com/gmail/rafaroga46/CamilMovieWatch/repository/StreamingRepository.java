package com.gmail.rafaroga46.CamilMovieWatch.repository;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.sun.jdi.LongValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository  extends JpaRepository<Streaming, Long> {

}
