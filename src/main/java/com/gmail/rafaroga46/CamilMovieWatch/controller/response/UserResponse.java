package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email) {

}
