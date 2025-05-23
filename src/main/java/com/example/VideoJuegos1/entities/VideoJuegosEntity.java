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
    //@GeneratedValue
    @JsonProperty("id")
    private UUID id;

    // Título del videojuego
    @JsonProperty("titulo")
    @NotBlank(message = "El título del videojuego es obligatorio")
    @Size(min = 2, max = 100, message = "El título debe tener entre 2 y 100 caracteres")
    private String titulo;

    // Año de lanzamiento, debe ser un valor entre 1900 y 2099
    @JsonProperty("anioLanzamiento")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "El año debe ser un número de 4 dígitos válido (1900-2099)")
    @Column(name = "anio_lanzamiento")
    private String   anioLanzamiento;

    // Plataforma en la que está disponible el videojuego
    @JsonProperty("plataforma")
    @NotBlank(message = "La plataforma es obligatoria")
    private String plataforma;

    // Duración en horas del videojuego
    @JsonProperty("duracionHoras")
    @NotNull(message = "La duración en horas es obligatoria")
    @Min(value = 1, message = "La duración debe ser de al menos 1 hora")
    @Column(name = "duracion_horas")
    private Integer duracionHoras;

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

    public String getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(String anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Integer getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(Integer duracionHoras) {
        this.duracionHoras = duracionHoras;
    }
}
