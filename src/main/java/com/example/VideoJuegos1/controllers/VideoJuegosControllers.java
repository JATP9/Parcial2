package com.example.VideoJuegos1.controllers;

import com.example.VideoJuegos1.entities.VideoJuegosEntity;
import com.example.VideoJuegos1.service.VideoJuegosService;

import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.*;

@RestController
@RequestMapping("/api/v1/videojuegos")
public class VideoJuegosControllers {

    private final VideoJuegosService videoJuegosService;

    public VideoJuegosControllers(VideoJuegosService videoJuegosService) {
        this.videoJuegosService = videoJuegosService;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int tamaño,
            @RequestParam(defaultValue = "titulo,asc") String[] orden) {

        Pageable pageable = PageRequest.of(pagina, tamaño, Sort.by(parsearOrden(orden)));
        return videoJuegosService.obtenerTodos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVideojuegoPorId(@PathVariable UUID id) {
        return videoJuegosService.obtenerVideojuegoPorId(id);
    }


    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "5") int tamaño,
            @RequestParam(defaultValue = "titulo,asc") String[] orden) {

        Pageable pageable = PageRequest.of(pagina, tamaño, Sort.by(parsearOrden(orden)));
        return videoJuegosService.buscarPorTitulo(titulo, pageable);
    }

    @PostMapping
    public ResponseEntity<?> agregarVideojuego(@Valid @RequestBody VideoJuegosEntity videojuego) {
        return videoJuegosService.agregarVideojuego(videojuego);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarVideojuego(@PathVariable UUID id, @Valid @RequestBody VideoJuegosEntity videojuego) {
        return videoJuegosService.actualizarVideojuego(id, videojuego);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarVideojuego(@PathVariable UUID id) {
        return videoJuegosService.eliminarVideojuego(id);
    }

    private Sort.Order parsearOrden(String[] orden) {
        if (orden.length < 2) {
            throw new IllegalArgumentException("El parámetro de orden debe incluir campo y dirección (por ejemplo: titulo,asc)");
        }
        return new Sort.Order(Sort.Direction.fromString(orden[1]), orden[0]);
    }
}
