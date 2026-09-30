package com.api.blogapi.dto.response;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PostResponseDto(
        UUID id,
        String autor,
        LocalDate data,
        String titulo,
        String texto,
        List<ComentarioResponseDto> comentarios
) {

}
