package com.api.blogapi.teste.context;

public record ContextoResponseDto(
        String locale,
        String timezone,
        String data,
        String moeda,
        String numero
) {
}
