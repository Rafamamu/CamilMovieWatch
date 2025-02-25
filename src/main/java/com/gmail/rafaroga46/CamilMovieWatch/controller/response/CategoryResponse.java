package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id,String nome) {
}
