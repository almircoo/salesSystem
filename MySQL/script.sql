-- Reiniciar la BD
DROP DATABASE IF EXISTS DB_LocalStore;
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
('Gamer', TRUE);

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

DELIMITER //
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
END //
DELIMITER ;

INSERT INTO Producto (nombre, stock, precio, estado, categoria_id) 
VALUES ('Laptop HP 15"', 10, 1299.99, TRUE, 1);

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

INSERT INTO Cliente (dni, nombre, email, telefono, direccion, fecha_nac)
VALUES ('44885881', 'Juan Diaz', 'juancito@gmail.com', '912345678', 'Callejon los Espinos', '1998-11-04');

DELIMITER //
CREATE PROCEDURE usp_Buscar_Cliente(IN p_busqueda VARCHAR(100))
BEGIN
    SELECT * FROM Cliente
    WHERE dni LIKE CONCAT('%', p_busqueda, '%')
       OR nombre LIKE CONCAT('%', p_busqueda, '%')
       OR email LIKE CONCAT('%', p_busqueda, '%')
       OR telefono LIKE CONCAT('%', p_busqueda, '%')
       OR direccion LIKE CONCAT('%', p_busqueda, '%');
END //
DELIMITER ;

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

-- Usuarios de prueba
INSERT INTO Usuario (nombre, clave, rol, fecha) VALUES 
('admin', 'admin123', 'ADMIN', CURDATE()),
('vendedor', 'vend123', 'VENDEDOR', CURDATE()),
('manager', 'manager123', 'MANAGER', CURDATE());

DELIMITER //
CREATE PROCEDURE usp_Buscar_Usuario(IN texto VARCHAR(100))
BEGIN
	SELECT * FROM Usuario u 
    WHERE u.nombre LIKE CONCAT('%', texto, '%') 
       OR u.rol LIKE CONCAT('%', texto, '%');
END //
DELIMITER ;

-- ============================================
-- TABLAS DE PEDIDOS Y DETALLE
-- ============================================
CREATE TABLE Pedido (
    pedido_id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT NOT NULL,
    fecha_pedido DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('PENDIENTE','PROCESANDO','EN_CAMINO','ENTREGADO','CANCELADO') NOT NULL DEFAULT 'PENDIENTE',
    total DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    observaciones TEXT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id)
);

CREATE TABLE DetallePedido (
    detalle_id INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES Pedido(pedido_id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES Producto(producto_id)
);

-- Pedidos de prueba
INSERT INTO Pedido (cliente_id, fecha_pedido, estado, total, observaciones) VALUES 
(1, '2025-08-21 08:30:00', 'EN_CAMINO', 1500.99, 'Entrega urgente'),
(1, '2025-08-20 17:15:00', 'PENDIENTE', 300.98, 'Cliente nuevo');

INSERT INTO DetallePedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES 
(1, 1, 1, 1299.99, 1299.99),
(2, 1, 2, 1299.99, 2599.98);

-- ============================================
-- PROCEDIMIENTOS DE REPORTES
-- ============================================
DELIMITER //
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
END //

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
END //

CREATE PROCEDURE usp_Reporte_General()
BEGIN
    SELECT 
        (SELECT COUNT(*) FROM Pedido WHERE estado != 'CANCELADO') AS total_pedidos,
        (SELECT SUM(total) FROM Pedido WHERE estado != 'CANCELADO') AS total_ventas,
        (SELECT COUNT(*) FROM Cliente) AS total_clientes,
        (SELECT COUNT(*) FROM Producto WHERE estado = TRUE) AS productos_activos,
        (SELECT COUNT(*) FROM Pedido WHERE estado = 'PENDIENTE') AS pedidos_pendientes;
END //
DELIMITER ;

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
CALL usp_Reporte_Ventas_Por_Fecha('2025-01-01', '2025-12-31');
CALL usp_Productos_Mas_Vendidos();
CALL usp_Reporte_General();
