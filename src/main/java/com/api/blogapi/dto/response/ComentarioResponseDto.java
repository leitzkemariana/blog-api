package com.api.blogapi.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record ComentarioResponseDto(
        UUID id,
        LocalDate data,
        String comentario
) {}
