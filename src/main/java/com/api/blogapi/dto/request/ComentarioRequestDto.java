package com.api.blogapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequestDto(
        @NotBlank(message = "O comentário não pode ser vazio")
        String comentario
) {}
