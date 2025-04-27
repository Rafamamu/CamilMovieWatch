package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.MovieRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.MovieResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Movie;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.MovieMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.Hibernate.map;

@RestController
@RequestMapping("/camilmoviewatch/movie")
@Tag(name = "Movie", description = "Recurso responsavel pelo grenciamento dos filmes")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    @Operation(summary = "Salvar filme",
            description = "Método responsável por realizar o salvamento de um novo filme",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Filme salvo com sucesso",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(request));

        return ResponseEntity.ok(MovieMapper.toMovieResponse(savedMovie));

    }


    @Operation(summary = "Buscar filmes",
            description = "Método responsável por buscar todos os filmes cadastrados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        return ResponseEntity.ok(movieService.findAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList());
    }


    @Operation(summary = "Buscar filmes por id",
            description = "Método responsável por buscar  os filmes por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna os filmes por id.",
            content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@Valid @PathVariable Long id) {
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Alterar filme",
            description = "Método responsável por alterar dados dos filmes",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Filme alterado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404",description = "Filme não encontrado.")
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@Valid @PathVariable Long id,
                                                @RequestBody MovieRequest request) {
        return movieService.update(id, MovieMapper.toMovie(request))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());


    }

    @Operation(summary = "Buscar por categoria",
            description = "Método responsável por buscar filmes por categoria.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Filme encontrado com sucesso",
            content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404",description = "Categoria não encontrada",content = @Content())
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@Valid @RequestParam Long category) {
        return ResponseEntity.ok(movieService.findByCategory(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());

    }


    @Operation(summary = "Deletar filmes por id",
            description = "Método responsável por deletar filmes por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204",description = "Filme deletado com sucesso", content = @Content())
    @ApiResponse(responseCode = "404",description = "Filme não encontrado",content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Movie> optMovie = movieService.findById(id);
        if (optMovie.isPresent()) {
            movieService.delete(id);

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}