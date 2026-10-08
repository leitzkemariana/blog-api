package com.api.blogapi.service;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {
    //List<PostResponseDto> findAll();
    Page<PostResponseDto> findAll(Pageable pageable);

    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

    ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto);
}
