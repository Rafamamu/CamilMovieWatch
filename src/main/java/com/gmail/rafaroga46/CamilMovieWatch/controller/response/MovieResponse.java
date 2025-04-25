package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
public record MovieResponse(@Schema(type = "Long",description = "id do filme")
                            Long id,
                            @Schema(type = "String",description = "Título do filme")
                            String title,
                            @Schema(type = "String",description = "Descrição do filme")
                            String description,
                            @Schema(type = "date",
                                    description = "data de lançamento do filme.ex:2025-05-08")
                            LocalDate releaseDate,
                            @Schema(type = "double",description = "Nota do filme.ex:8.5")
                            double rating,
                            @Schema(type = "array",description = "Lista de categorias")
                            List<CategoryResponse> categories,
                            @Schema(type = "array",description = "Lista de streamings")
                            List<StreamingResponse> streamings) {



}
