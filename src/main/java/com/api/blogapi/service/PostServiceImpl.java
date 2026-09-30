package com.api.blogapi.service;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;
import com.api.blogapi.mapper.ComentarioMapper;
import com.api.blogapi.mapper.PostMapper;
import com.api.blogapi.model.ComentarioModel;
import com.api.blogapi.model.PostModel;
import com.api.blogapi.repository.ComentarioRepository;
import com.api.blogapi.repository.PostRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> findAll(){
        List<PostModel> posts = postRepository.findAll();
        List<PostResponseDto> dtos = new ArrayList<>();

        for (PostModel post : posts) {
            dtos.add(postMapper.toDto(post));
        }
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto findById(UUID id){
        Optional<PostModel> optionalPost = postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new RuntimeException("Post não encontrado com o ID: " + id);
        }

        PostModel post = optionalPost.get();
        return postMapper.toDto(post);
    }

    @Override
    @Transactional()
    public PostResponseDto createPost(PostRequestDto dto){
        PostModel post = postMapper.toEntity(dto);
        PostModel saved = postRepository.save(post);
        return postMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ComentarioResponseDto addComentario(UUID postId, ComentarioRequestDto dto) {
        Optional<PostModel> optionalPost = postRepository.findById(postId);
        PostModel post = optionalPost.get();

        ComentarioModel comentario = new ComentarioModel(dto.comentario(), post);
        post.adicionarComentario(comentario);

        ComentarioModel saved = comentarioRepository.save(comentario);

        return comentarioMapper.toDto(saved);
    }
}
