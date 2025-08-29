-- Crear la BD
CREATE DATABASE DB_LocalStore;
USE DB_LocalStore;

-- ============================================
-- TABLA CATEGORIA
-- ============================================
CREATE TABLE Categoria (
    categoria_id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (categoria_id)
);

INSERT INTO Categoria (nombre, estado) VALUES
('Gamer', TRUE),
('Laptops', TRUE);

-- ============================================
-- TABLA PRODUCTO
-- ============================================
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

-- procedimiento para buscar producto
CREATE PROCEDURE usp_Buscar_producto(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT
        p.producto_id,
        p.nombre,
        p.stock,
        p.precio,
        p.estado AS estado,
        c.categoria_id,
        c.nombre AS categoria
    FROM Producto p
    INNER JOIN Categoria c ON p.categoria_id = c.categoria_id
    WHERE p.nombre LIKE CONCAT('%', p_busqueda, '%')
    ORDER BY p.nombre;
END

-- insertar producto
INSERT INTO Producto (nombre, stock, precio, estado, categoria_id)
VALUES ('MacBook pro"', 2, 2999.99, TRUE, 1),
       ('Laptop HP 15"', 10, 1299.99, TRUE, 2);

-- ============================================
-- TABLA CLIENTE
-- ============================================
CREATE TABLE Cliente (
    cliente_id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_nac DATE
);

-- insertar clientes
INSERT INTO Cliente (dni, nombre, email, telefono, direccion, fecha_nac) VALUES
('44885661', 'Lucas Aldez', 'juancito@gmail.com', '912345678', 'Av. los Espinos', '1998-11-04'),
('44885770', 'Geral Causa', 'geralc@gmail.com', '912345978', 'Av. lima 123', '2000-08-04');

-- procedimiento para bsucar cliente
CREATE PROCEDURE usp_Buscar_Cliente(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT * FROM Cliente
    WHERE dni LIKE CONCAT('%', p_busqueda, '%')
       OR nombre LIKE CONCAT('%', p_busqueda, '%')
       OR email LIKE CONCAT('%', p_busqueda, '%')
       OR telefono LIKE CONCAT('%', p_busqueda, '%')
       OR direccion LIKE CONCAT('%', p_busqueda, '%');
END

-- ============================================
-- TABLA USUARIO
-- ============================================
CREATE TABLE Usuario (
	usuario_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(100) NOT NULL,
	clave VARCHAR(100) NOT NULL,
	rol VARCHAR(100),
 	fecha DATE NULL
);

-- insertar usuarios
INSERT INTO Usuario (nombre, clave, rol, fecha) VALUES
('admin', '123', 'ADMIN', CURDATE()),
('vendedor', '123', 'VENDEDOR', CURDATE()),
('manager', '123', 'MANAGER', CURDATE());

-- procedimeinto para buscar
CREATE PROCEDURE usp_Buscar_Usuario(IN texto VARCHAR(100))
BEGIN
	SELECT * FROM Usuario u
    WHERE u.nombre LIKE CONCAT('%', texto, '%')
       OR u.rol LIKE CONCAT('%', texto, '%');
END

-- ============================================
-- TABLA PEDIDO Y DETALLE PEDIOD
-- ============================================
CREATE TABLE Pedido (
    pedido_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    usuario_id INT NOT NULL,
    fecha_pedido DATETIME NOT NULL,
    tipo_comprobante VARCHAR(30) NOT NULL DEFAULT 'BOLETA',
    numero_comprobante VARCHAR(20) UNIQUE,
    subtotal DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    igv DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    total DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    metodo_pago VARCHAR(30) NOT NULL DEFAULT 'EFECTIVO',
    estado VARCHAR(30) NOT NULL DEFAULT 'PENDIENTE',
    observaciones TEXT,

    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE RESTRICT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(usuario_id) ON DELETE RESTRICT
);

-- tabla DetallePedido
CREATE TABLE DetallePedido (
    detalle_id INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL DEFAULT 1,
    precio_unitario DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    subtotal DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    FOREIGN KEY (pedido_id) REFERENCES Pedido(pedido_id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id) ON DELETE RESTRICT
);

-- insertar pedido
INSERT INTO Pedido (cliente_id, usuario_id, fecha_pedido, tipo_comprobante, numero_comprobante, subtotal, igv, total, metodo_pago, estado, observaciones)
VALUES
(1, 1, NOW(), 'BOLETA', 'B001-0001', 100.00, 18.00, 118.00, 'EFECTIVO', 'PENDIENTE', 'Primer pedido de prueba'),
(1, 1, NOW(), 'FACTURA', 'F001-0001', 250.50, 45.09, 295.59, 'TARJETA', 'PROCESANDO', 'Cliente preferencial');
-- insertar detalles pedido
INSERT INTO DetallePedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 2, 50.00, 100.00),
(2, 2, 1, 150.50, 150.50),
(2, 2, 2, 50.00, 100.00);

-- buscar pedido por id
CREATE PROCEDURE usp_Buscar_Pedido_Por_Id(IN p_pedido_id INT)
BEGIN
    -- Buscar información principal del pedido
    SELECT
        p.pedido_id,
        p.cliente_id,
        p.usuario_id,
        p.fecha_pedido,
        p.tipo_comprobante,
        p.numero_comprobante,
        p.subtotal,
        p.igv,
        p.total,
        p.metodo_pago,
        p.estado,
        p.observaciones,
        -- Información del cliente
        c.nombre AS cliente_nombre,
        c.email AS cliente_email,
        c.telefono AS cliente_telefono,
        c.direccion AS cliente_direccion,
        -- Información del usuario/vendedor
        u.nombre AS usuario_nombre
    FROM Pedido p
    INNER JOIN Cliente c ON p.cliente_id = c.cliente_id
    INNER JOIN Usuario u ON p.usuario_id = u.usuario_id
    WHERE p.pedido_id = p_pedido_id;

    -- Buscar detalles del pedido
    SELECT
        dp.detalle_id,
        dp.pedido_id,
        dp.producto_id,
        dp.cantidad,
        dp.precio_unitario,
        dp.subtotal AS detalle_subtotal,
        -- Información del producto
        pr.nombre AS producto_nombre
    FROM DetallePedido dp
    INNER JOIN Producto pr ON dp.producto_id = pr.producto_id
    WHERE dp.pedido_id = p_pedido_id
    ORDER BY dp.detalle_id;
END

-- ============================================
-- PROCEDIMIENTOS DE REPORTE
-- ============================================
CREATE PROCEDURE usp_Reporte_Ventas_Por_Fecha(
    IN fecha_inicio DATE,
    IN fecha_fin DATE
)
BEGIN
    SELECT
        DATE(p.fecha_pedido) AS fecha,
        COUNT(p.pedido_id) AS total_pedidos,
        SUM(p.total) AS total_ventas,
        AVG(p.total) AS promedio_venta
    FROM Pedido p
    WHERE DATE(p.fecha_pedido) BETWEEN fecha_inicio AND fecha_fin
      AND p.estado != 'CANCELADO'
    GROUP BY DATE(p.fecha_pedido)
    ORDER BY fecha DESC;
END

CREATE PROCEDURE usp_Productos_Mas_Vendidos()
BEGIN
    SELECT
        pr.nombre AS producto,
        SUM(dp.cantidad) AS total_vendido,
        SUM(dp.subtotal) AS total_ingresos
    FROM DetallePedido dp
    INNER JOIN Producto pr ON dp.producto_id = pr.producto_id
    INNER JOIN Pedido p ON dp.pedido_id = p.pedido_id
    WHERE p.estado != 'CANCELADO'
    GROUP BY pr.producto_id, pr.nombre
    ORDER BY total_vendido DESC
    LIMIT 10;
END

CREATE PROCEDURE usp_Reporte_General()
BEGIN
    SELECT
        (SELECT COUNT(*) FROM Pedido WHERE estado != 'CANCELADO') AS total_pedidos,
        (SELECT SUM(total) FROM Pedido WHERE estado != 'CANCELADO') AS total_ventas,
        (SELECT COUNT(*) FROM Cliente) AS total_clientes,
        (SELECT COUNT(*) FROM Producto WHERE estado = TRUE) AS productos_activos,
        (SELECT COUNT(*) FROM Pedido WHERE estado = 'PENDIENTE') AS pedidos_pendientes;
END

-- ============================================
-- CONSULTAS DE PRUEBA
-- ============================================
SELECT * FROM Categoria;
SELECT * FROM Producto;
SELECT * FROM Cliente;
SELECT * FROM Usuario;
SELECT * FROM Pedido;
SELECT * FROM DetallePedido;

CALL usp_Buscar_producto('lap');
CALL usp_Buscar_Cliente('juan');
CALL usp_Buscar_Usuario('man');
CALL usp_Buscar_Pedido_Por_Id(1);
CALL usp_Reporte_Ventas_Por_Fecha('2025-01-01', '2025-12-31');
CALL usp_Productos_Mas_Vendidos();
CALL usp_Reporte_General();
