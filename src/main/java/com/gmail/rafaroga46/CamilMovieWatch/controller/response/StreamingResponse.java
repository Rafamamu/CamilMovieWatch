package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StreamingResponse(@Schema(type = "Long",description = "Id do Streaming")
                                Long id,
                                @Schema(type = "String",description = "Nome do streaming")
                                String name) {
}
