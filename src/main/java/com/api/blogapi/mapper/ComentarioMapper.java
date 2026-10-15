package com.api.blogapi.mapper;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.model.ComentarioModel;
import com.api.blogapi.model.PostModel;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {
    public ComentarioModel toEntity(ComentarioRequestDto dto, PostModel entity) {
        if (dto == null) {
            return null;
        }
        return new ComentarioModel(
                dto.comentario().toString(),
                entity
        );
    }

    public ComentarioResponseDto toDto(ComentarioModel entity) {
        if (entity == null) {
            return null;
        }
        return new ComentarioResponseDto(
                entity.getId(),
                entity.getData(),
                entity.getComentario()
        );
    }

    public void updateEntityFromDto(ComentarioRequestDto dto, ComentarioModel entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setComentario(dto.comentario());
    }
}
