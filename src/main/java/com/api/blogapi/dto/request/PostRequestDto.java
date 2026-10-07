package com.api.blogapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequestDto(
        @NotBlank(message = "{post.autor.obrigatorio}")
        @Size(max = 70, message = "{post.autor.tamanho}")
        String autor,

        @NotBlank(message = "{post.titulo.obrigatorio}")
        @Size(max = 100, message = "{post.titulo.tamanho}")
        String titulo,

        @NotBlank(message = "{post.texto.obrigatorio}")
        String texto
) {}
