package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.StreamingRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.StreamingResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.StreamingMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/camilmoviewatch/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping()
    public  ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        List<StreamingResponse> streamings = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streamings);

    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(savedStreaming));

    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());


    }

    @DeleteMapping("/{id}")
    public void deleteByStreamingId(@PathVariable Long id) {
        streamingService.delete(id);
    }

}
