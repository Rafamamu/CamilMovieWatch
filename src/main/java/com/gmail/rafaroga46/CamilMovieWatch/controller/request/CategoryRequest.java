package com.gmail.rafaroga46.CamilMovieWatch.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Nome da categoria é obrigatório.") String name) {
}
