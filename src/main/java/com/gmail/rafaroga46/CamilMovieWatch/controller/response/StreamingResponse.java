package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import lombok.Builder;

@Builder
public record StreamingResponse(Long id, String name) {
}
