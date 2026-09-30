package com.api.blogapi.controller;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;
import com.api.blogapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto dto){
        PostResponseDto created = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentarioResponseDto> createComentario(
            @PathVariable UUID postId,
            @RequestBody @Valid ComentarioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addComentario(postId, dto));
    }
}
