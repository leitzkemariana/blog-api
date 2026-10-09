package com.api.blogapi.controller;

import com.api.blogapi.dto.request.ComentarioRequestDto;
import com.api.blogapi.dto.request.PostRequestDto;
import com.api.blogapi.dto.response.ComentarioResponseDto;
import com.api.blogapi.dto.response.PostResponseDto;
import com.api.blogapi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="Posts e comentários", description = "Operações do Blog API")
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "Lista posts com paginação")
    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> getAllPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "data") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam(required = false) String titulo){

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(postService.findAll(pageable, titulo));
    }

    @Operation(summary = "Mostra post do id informado")
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable UUID id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @Operation(summary = "Cria post")
    @PostMapping("/newpost")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody @Valid PostRequestDto dto){
        PostResponseDto created = postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Adiciona comentário no post")
    @PostMapping("/comentarios/{postId}")
    public ResponseEntity<ComentarioResponseDto> createComentario(
            @PathVariable UUID postId,
            @RequestBody @Valid ComentarioRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.addComentario(postId, dto));
    }
}
