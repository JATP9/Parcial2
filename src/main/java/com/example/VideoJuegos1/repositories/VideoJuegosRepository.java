package com.example.VideoJuegos1.repositories;

import com.example.VideoJuegos1.entities.VideoJuegosEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoJuegosRepository extends JpaRepository<VideoJuegosEntity, UUID> {

    Page<VideoJuegosEntity> findAllByTituloContaining(String titulo, Pageable pageable);
}