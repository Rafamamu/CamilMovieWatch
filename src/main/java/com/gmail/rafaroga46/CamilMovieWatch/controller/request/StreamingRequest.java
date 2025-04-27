package com.gmail.rafaroga46.CamilMovieWatch.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@Schema(type = "String", description = "Nome do streaming")
                               @NotEmpty(message = "Nome do serviço de streaming é obrigatório.")
                               String name) {
}
