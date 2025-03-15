package com.gmail.rafaroga46.CamilMovieWatch.controller.request;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MovieRequest(String title,
                           String description,
                           LocalDate releaseDate,
                           double rating,
                           List<Long> categories,
                           List<Long> streamings) {
}
