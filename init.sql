-- Inicialización de la base de datos para la API de productos
CREATE TABLE IF NOT EXISTS Producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    articulo VARCHAR(100) NOT NULL,
    categoria VARCHAR(50),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    codigo INT UNIQUE NOT NULL
);