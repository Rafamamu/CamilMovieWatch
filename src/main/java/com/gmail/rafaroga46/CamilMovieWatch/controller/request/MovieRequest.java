package com.gmail.rafaroga46.CamilMovieWatch.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MovieRequest(@Schema(type = "String", description = "Nome do filme")
                           @NotEmpty(message = "Título do filme é obrigatório.")
                           String title,
                           @Schema(type = "String", description = "descrição do filme ")
                           String description,
                           @Schema(type = "date", description =
                                   "Data de lançamento do filme. ex:2025-05-08")
                           LocalDate releaseDate,
                           @Schema(type = "double", description = "Nota do filme.ex: 8.5")
                           double rating,
                           @Schema(type = "array", description = "Lista de categorias")
                           List<Long> categories,
                           @Schema(type = "array", description = "Lista de streamings")
                           List<Long> streamings) {
}
