package com.gmail.rafaroga46.CamilMovieWatch.mapper;

import com.gmail.rafaroga46.CamilMovieWatch.controller.request.StreamingRequest;
import com.gmail.rafaroga46.CamilMovieWatch.controller.response.StreamingResponse;
import com.gmail.rafaroga46.CamilMovieWatch.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {


    //Estou transformando a requisição(request) em um streaming para ser salvo no banco!

    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming
                .builder()
                .name(request.name())
                .build();
    }

    // Agora estou pegando o streaming e transformando em response para devolver para o usuário
    // retornando o id e o nome.

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return  StreamingResponse
                .builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
