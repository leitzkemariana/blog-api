package com.api.blogapi.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PostResponseDto(
        UUID id,
        String autor,
        LocalDate data,
        String titulo,
        String texto
) {

}
