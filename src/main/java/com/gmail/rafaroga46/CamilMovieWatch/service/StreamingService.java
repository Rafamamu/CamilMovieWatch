package com.gmail.rafaroga46.CamilMovieWatch.service;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.gmail.rafaroga46.CamilMovieWatch.repository.StreamingRepository;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public StreamingService(StreamingRepository streamingRepository) {
        this.streamingRepository = streamingRepository;
    }

    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return  streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(Long id) {
        return streamingRepository.findById(AbstractPersistable_.id);
    }

    public void delete(Long id) {
        streamingRepository.deleteById(id);
    }


}
