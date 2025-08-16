use bd_cibertec

CREATE TABLE Categoria (
    categoria_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (categoria_id)
);

INSERT INTO Categoria (nombre, estado) 
VALUES ('Gamer', TRUE);

select * from categoria;

CREATE TABLE Producto (
    producto_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    precio DECIMAL(10,2) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    categoria_id INT NOT NULL,
    PRIMARY KEY (producto_id),
    FOREIGN KEY (categoria_id) REFERENCES Categoria(categoria_id)
);

CREATE PROCEDURE usp_Buscar_producto(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT 
        p.producto_id,
        p.nombre,
        p.stock,
        p.precio,
        p.estado as estado,
        c.categoria_id,
        c.nombre AS categoria
    FROM 
        Producto p
    INNER JOIN 
        Categoria c ON p.categoria_id = c.categoria_id
    WHERE 
        p.nombre LIKE CONCAT('%', p_busqueda, '%')
    ORDER BY 
        p.nombre;
END 

INSERT INTO Producto (nombre, stock, precio, estado, categoria_id) 
VALUES ('Laptop HP 15"', 10, 1299.99, TRUE, 1);

select * from producto;

CALL usp_Buscar_producto('lap');


CREATE TABLE Cliente (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_nac DATE
);

select * from cliente;

INSERT INTO Cliente (dni, nombre, email, telefono, direccion, fecha_nac)
VALUES ('44885522', 'Frans Mendoza', 'juancito@gmail.com', '912345678', 'Callejon los Espinos', '1990-09-04');

CREATE PROCEDURE usp_Buscar_Cliente(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT * FROM Cliente
    WHERE dni LIKE CONCAT('%', p_busqueda, '%')
       OR nombre LIKE CONCAT('%', p_busqueda, '%')
       OR email LIKE CONCAT('%', p_busqueda, '%')
       OR telefono LIKE CONCAT('%', p_busqueda, '%')
       OR direccion LIKE CONCAT('%', p_busqueda, '%');
end

CALL usp_Buscar_Cliente('fran');
