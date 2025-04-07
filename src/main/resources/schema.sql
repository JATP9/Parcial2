CREATE TABLE IF NOT EXISTS video_juegos_entity (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    anio_lanzamiento VARCHAR(4) NOT NULL,
    duracion_horas INTEGER NOT NULL,
    plataforma VARCHAR(100) NOT NULL
    );