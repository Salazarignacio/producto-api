CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30),
    articulo VARCHAR(60),
    categoria VARCHAR(40),
    precio DOUBLE,
    stock INT
);

