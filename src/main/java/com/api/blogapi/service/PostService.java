package com.api.blogapi.service;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostResponseDto> findAll();
    
    PostResponseDto findById(UUID id);

    PostResponseDto createPost(PostRequestDto dto);

    ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto);
}
