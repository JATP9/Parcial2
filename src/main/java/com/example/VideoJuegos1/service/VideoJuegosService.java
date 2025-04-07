package com.example.VideoJuegos1.service;

import com.example.VideoJuegos1.entities.VideoJuegosEntity;
import com.example.VideoJuegos1.repositories.VideoJuegosRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoJuegosService {

    private final VideoJuegosRepository repositorio;

    public VideoJuegosService(VideoJuegosRepository repositorio) {
        this.repositorio = repositorio;
    }

    public Page<VideoJuegosEntity> listarVideojuegos(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    public ResponseEntity<?> obtenerTodos(Pageable pageable) {
        Page<VideoJuegosEntity> pagina = repositorio.findAll(pageable);
        return construirRespuesta(pagina);
    }

    public ResponseEntity<?> obtenerVideojuegoPorId(UUID id) {
        Optional<VideoJuegosEntity> videojuego = repositorio.findById(id);
        if (videojuego.isEmpty()) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", String.format("No se encontró un videojuego con ID %s.", id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        return ResponseEntity.ok(Collections.singletonMap("videojuego", videojuego.get()));
    }


    public ResponseEntity<?> buscarPorTitulo(String titulo, Pageable pageable) {
        Page<VideoJuegosEntity> pagina = repositorio.findAllByTituloContaining(titulo, pageable);
        return construirRespuesta(pagina);
    }

    public ResponseEntity<?> agregarVideojuego(VideoJuegosEntity nuevo) {
        Page<VideoJuegosEntity> existentes = repositorio.findAllByTituloContaining(nuevo.getTitulo(), Pageable.unpaged());
        if (!existentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Collections.singletonMap("mensaje", "Ya existe un videojuego con ese título."));
        }
        VideoJuegosEntity guardado = repositorio.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("mensaje", "Videojuego creado con ID: " + guardado.getId()));
    }

    public ResponseEntity<?> actualizarVideojuego(UUID id, VideoJuegosEntity actualizado) {
        return repositorio.findById(id).map(vj -> {
            vj.setTitulo(actualizado.getTitulo());
            vj.setAnioLanzamiento(actualizado.getAnioLanzamiento());
            vj.setPlataforma(actualizado.getPlataforma());
            vj.setDuracionHoras(actualizado.getDuracionHoras());
            repositorio.save(vj);
            return ResponseEntity.ok(Collections.singletonMap("mensaje", "Videojuego actualizado con ID: " + vj.getId()));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("mensaje", "Videojuego no encontrado con ID: " + id)));
    }

    public ResponseEntity<?> eliminarVideojuego(UUID id) {
        return repositorio.findById(id).map(vj -> {
            repositorio.deleteById(id);
            return ResponseEntity.ok(Collections.singletonMap("mensaje", "Videojuego eliminado con ID: " + id));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Collections.singletonMap("mensaje", "No se encontró el videojuego con ID: " + id)));
    }

    private ResponseEntity<?> construirRespuesta(Page<VideoJuegosEntity> pagina) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("totalElementos", pagina.getTotalElements());
        respuesta.put("totalPaginas", pagina.getTotalPages());
        respuesta.put("paginaActual", pagina.getNumber());
        respuesta.put("cantidadElementos", pagina.getNumberOfElements());
        respuesta.put("videojuegos", pagina.getContent());
        return ResponseEntity.ok(respuesta);
    }
}