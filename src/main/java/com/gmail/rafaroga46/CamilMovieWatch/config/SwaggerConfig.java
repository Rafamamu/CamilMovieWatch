package com.gmail.rafaroga46.CamilMovieWatch.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPI() {

        Contact contact = new Contact();
        contact.name("Rafael");
        contact.email("rafaroga46@gmail.com");

        Info info = new Info();
        info.title("CamilMovieFlix");
        info.version("v1");
        info.description("Aplicação para o gerenciamento de catálogo de filmes");
        info.contact(contact);

        return new OpenAPI().info(info);


    }
}
