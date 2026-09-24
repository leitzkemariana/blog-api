package com.api.blogapi.repository;

import com.api.blogapi.model.ComentarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, UUID> {
}
