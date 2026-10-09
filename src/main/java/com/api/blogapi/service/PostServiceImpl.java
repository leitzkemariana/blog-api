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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<PostResponseDto> findAll(Pageable pageable) {
        Page<PostModel> posts = postRepository.findAll(pageable);

        return posts.map(post -> {
            PostResponseDto dto = postMapper.toDto(post);
            return dto;
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAll(Pageable pageable, String titulo) {
        Page<PostModel> posts;

        if (titulo == null || titulo.isBlank()){
            posts = postRepository.findAll(pageable);
        } else {
            posts = postRepository.findByTituloContainingIgnoreCase(titulo, pageable);
        }

        List<PostResponseDto> response = new ArrayList<>();

        for (PostModel post : posts.getContent()) {
            PostResponseDto dto = postMapper.toDto(post);
            response.add(dto);
        }

        return new PageImpl<>(response, pageable, posts.getTotalElements());
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

    //  @Override
//  @Transactional
//  public PostResponseDto updatePost(UUID id, PostRequestDto dto) {
//      Optional<PostModel> optionalPost = postRepository.findById(id);
//
//      if (optionalPost.isEmpty()) {
//          throw new RuntimeException("Post não encontrado com o ID: " + id);
//      }
//
//      PostModel post = optionalPost.get();
//      postMapper.updateEntityFromDto(dto, post);
//
//      PostModel updatedPost = postRepository.save(post);
//      return postMapper.toDto(updatedPost);
//  }

//  @Override
//  @Transactional
//  public void deletePost(UUID id) {
//      Optional<PostModel> optionalPost = postRepository.findById(id);
//
//      if (optionalPost.isEmpty()) {
//          throw new RuntimeException("Post não encontrado com o ID: " + id);
//      }
//
//      PostModel post = optionalPost.get();
//      postRepository.delete(post);
//  }

//    @Override
//    @Transactional
//    public void deleteComentario(UUID comentarioId) {
//
//    	 Optional<ComentarioModel> optionalComentario = comentarioRepository.findById(comentarioId);
//
//         if (optionalComentario.isEmpty()) {
//             throw new RuntimeException("Comentário não encontrado com o ID: " + comentarioId);
//         }
//         ComentarioModel comentario = optionalComentario.get();
//         comentarioRepository.delete(comentario);
//    }
}
