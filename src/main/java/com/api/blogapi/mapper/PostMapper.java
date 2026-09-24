package com.api.blogapi.mapper;

import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.PostResponseDto;
import com.api.blogapi.model.PostModel;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

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
        return new PostResponseDto(
                entity.getId(),
                entity.getAutor(),
                entity.getData(),
                entity.getTitulo(),
                entity.getTexto()
        );
    }
}
