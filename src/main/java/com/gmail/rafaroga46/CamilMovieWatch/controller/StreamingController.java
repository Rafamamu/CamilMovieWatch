package com.gmail.rafaroga46.CamilMovieWatch.controller;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.StreamingRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.StreamingResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import com.gmail.rafaroga46.CamilMovieWatch.mapper.StreamingMapper;
import com.gmail.rafaroga46.CamilMovieWatch.service.StreamingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/camilmoviewatch/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }


    @Operation(summary = "Listar todas streamings",
            description = "Método responsável por listar todas streamings",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Lista todas as stramings formato JSON.",
            content = @Content(array =
            @ArraySchema(schema = @Schema(implementation = StreamingResponse.class))))
    @GetMapping()
    public  ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        List<StreamingResponse> streamings = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streamings);

    }


    @Operation(summary = "Salvar streaming",
            description = "Método responsável por salvar um novo streaming",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201",description = "Streaming salvo com sucesso.",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(savedStreaming));

    }

    @Operation(summary = "Buscar Streming por id",
            description = "Método responsável por encontrar um streaming por id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",
            description = "Streaming encontrado com sucesso",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class)))
    @ApiResponse(responseCode = "404",description = "Streaming não encontrado.")
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());


    }

    @Operation(summary = "Deletar streaming por id",
            description = "Método responsável por deletar um streaming por id",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204",
            description = "Streaming deletado com sucesso.",content = @Content())
    @ApiResponse(responseCode = "404",description = "Streaming não encontrado.",content = @Content())
    @DeleteMapping("/{id}")
    public void deleteByStreamingId(@PathVariable Long id) {

        streamingService.delete(id);
    }

}
