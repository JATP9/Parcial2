CREATE TABLE IF NOT EXISTS video_juegos_entity (
                                                   id VARCHAR(136) NOT NULL PRIMARY KEY,
                                                   titulo VARCHAR(255) NOT NULL,
                                                   anio_lanzamiento INT NOT NULL,
                                                   plataforma VARCHAR(100),
                                                   duracion_horas INT
);
