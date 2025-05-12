package com.example.VideoJuegos1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "video_juegos_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoJuegosEntity {


    // ID único generado automáticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private UUID id;

    // Título del videojuego
    @JsonProperty("titulo")
    @NotBlank(message = "El título del videojuego es obligatorio")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
    private String titulo;

    // Año de lanzamiento, debe ser un valor entre 1900 y 2099
    @JsonProperty("anio_lanzamiento")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "El año debe ser un número de 4 dígitos válido (1900-2099)")
    //@Column(name = "anio_lanzamiento")
    private String anio_lanzamiento;

    // Plataforma en la que está disponible el videojuego
    @JsonProperty("plataforma")
    @NotBlank(message = "La plataforma es obligatoria")
    private String plataforma;

    // Duración en horas del videojuego
    @JsonProperty("duracion_horas")
    @NotNull(message = "La duración en horas es obligatoria")
    @Min(value = 1, message = "La duración debe ser de al menos 1 hora")
    //@Column(name = "duracion_horas")
    private Integer duracion_horas;

    // Método para generar un UUID si no está presente
    @PrePersist
    public void generarUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    // Getters y Setters (Lombok los genera automáticamente gracias a @Data)

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getAnio_lanzamiento() {
        return anio_lanzamiento;
    }


    public void setAnio_lanzamiento(String anio_lanzamiento) {
        this.anio_lanzamiento = anio_lanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }


    public Integer getDuracion_horas() {
        return duracion_horas;
    }

    public void setDuracion_horas(Integer duracion_horas) {
        this.duracion_horas = duracion_horas;
    }

}
