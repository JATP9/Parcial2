CREATE TABLE IF NOT EXISTS video_juegos_entity (
                                                   id VARCHAR(36) PRIMARY KEY,
                                                   titulo VARCHAR(100) NOT NULL,
                                                   anio_lanzamiento VARCHAR(4) NOT NULL,
                                                   plataforma VARCHAR(255) NOT NULL,
                                                   duracion_horas INT NOT NULL
);

