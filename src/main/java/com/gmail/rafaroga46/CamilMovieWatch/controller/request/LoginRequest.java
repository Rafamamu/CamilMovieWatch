package com.gmail.rafaroga46.CamilMovieWatch.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRequest(@Schema(type = "String",description = "Email do usuário")
                           String email,
                           @Schema(type = "String",description = "Senha do usuário")
                           String password ) {

}
