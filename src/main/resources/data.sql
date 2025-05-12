INSERT INTO video_juegos_entity (id, titulo, anio_lanzamiento, plataforma, duracion_horas) VALUES
                                                                                               (UUID(), 'Portal 2', 2011, 'PC', 10),
                                                                                               (UUID(), 'Red Dead Redemption 2', 2018, 'PlayStation 4', 80),
                                                                                               (UUID(), 'Stardew Valley', 2016, 'Nintendo Switch', 80),
                                                                                               (UUID(), 'The Legend of Zelda: Breath of the Wild', 2017, 'Nintendo Switch', 60),
                                                                                               (UUID(), 'The Witcher 3: Wild Hunt', 2015, 'PC', 100),
                                                                                               (UUID(), 'God of War', 2018, 'PlayStation 4', 50),
                                                                                               (UUID(), 'Celeste', 2018, 'PC', 10),
                                                                                               (UUID(), 'Elden Ring', 2022, 'PC', 120),
                                                                                               (UUID(), 'Dark Souls III', 2016, 'PC', 70),
                                                                                               (UUID(), 'Hollow Knight', 2017, 'PC', 40)
ON DUPLICATE KEY UPDATE id = id;
