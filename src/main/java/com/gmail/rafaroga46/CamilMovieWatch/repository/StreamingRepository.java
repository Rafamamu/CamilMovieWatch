package com.gmail.rafaroga46.CamilMovieWatch.repository;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.sun.jdi.LongValue;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface StreamingRepository  extends JpaRepository<Streaming, Long> {


}
