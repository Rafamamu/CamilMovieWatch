package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(@Schema(type = "String",description = "Token de login ")
                            String token) {

}
