package com.api.blogapi.mapper;

import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;
import com.api.blogapi.model.ComentarioModel;
import com.api.blogapi.model.PostModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    private final ComentarioMapper comentarioMapper;

    public PostMapper(ComentarioMapper comentarioMapper) {
        this.comentarioMapper = comentarioMapper;
    }

    public PostModel toEntity(PostRequestDto dto){
        if (dto == null){
            return null;
        }
        return new PostModel(
                dto.autor(),
                dto.titulo(),
                dto.texto()
        );
    }

    public PostResponseDto toDto(PostModel entity){
        if (entity == null){
            return null;
        }

        List<ComentarioResponseDto> comentariosDto = new ArrayList<>();

        if (entity.getComentarios() != null) {
            for (ComentarioModel comentario : entity.getComentarios()) {
                ComentarioResponseDto dto = comentarioMapper.toDto(comentario);
                comentariosDto.add(dto);
            }
        }

        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto(),
                comentariosDto
        );
    }
}
