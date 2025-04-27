package com.gmail.rafaroga46.CamilMovieWatch.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record UserResponse(@Schema(type = "Long",description = "Id de usuário.")
                           Long id,
                           @Schema(type = "String",description = "Nome de Usuário.")
                           String name,
                           @Schema(type = "String",description = "Email do usuário.")
                           String email) {

}
