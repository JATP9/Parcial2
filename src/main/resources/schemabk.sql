CREATE TABLE IF NOT EXISTS video_juegos_entity (
                                                   id CHAR(36) PRIMARY KEY,
                                                   titulo VARCHAR(255) NOT NULL,
                                                   anioLanzamiento INT NOT NULL,
                                                   plataforma VARCHAR(255) NOT NULL,
                                                   duracionHoras INT NOT NULL
);


