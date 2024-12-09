-- Creación de la base de datos
CREATE DATABASE tecsup_parking;
USE tecsup_parking;

-- Tabla usuarios
CREATE TABLE usuarios (
    pk_id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(15) UNIQUE NOT NULL,
    codigo_estudiante VARCHAR(20),
    nombre VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('profesor', 'alumno', 'trabajador', 'externo') NOT NULL,
    preferencia_estacionamiento INT,
    qr VARCHAR(255) UNIQUE NOT NULL
);

-- Tabla vehiculos
CREATE TABLE vehiculos (
    pk_id_vehiculo INT AUTO_INCREMENT PRIMARY KEY,
    fk_id_usuario INT NOT NULL,
    placa VARCHAR(20) UNIQUE NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(30) NOT NULL,
    anio INT NOT NULL,
    FOREIGN KEY (fk_id_usuario) REFERENCES usuarios(pk_id_usuario)
);

-- Tabla estacionamientos
CREATE TABLE estacionamientos (
    pk_id_estacionamiento INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo ENUM('trabajadores', 'general') NOT NULL,
    capacidad INT NOT NULL
);

-- Tabla espacios
CREATE TABLE espacios (
    pk_id_espacio INT AUTO_INCREMENT PRIMARY KEY,
    fk_id_estacionamiento INT NOT NULL,
    codigo_espacio VARCHAR(10) NOT NULL,
    estado ENUM('disponible', 'ocupado') DEFAULT 'disponible',
    FOREIGN KEY (fk_id_estacionamiento) REFERENCES estacionamientos(pk_id_estacionamiento)
);

-- Tabla movimientos
CREATE TABLE movimientos (
    pk_id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    fk_id_usuario INT NOT NULL,
    fk_id_vehiculo INT NOT NULL,
    fk_id_estacionamiento INT NOT NULL,
    fk_id_espacio INT NOT NULL,
    fecha_hora_entrada DATETIME NOT NULL,
    fecha_hora_salida DATETIME,
    FOREIGN KEY (fk_id_usuario) REFERENCES usuarios(pk_id_usuario),
    FOREIGN KEY (fk_id_vehiculo) REFERENCES vehiculos(pk_id_vehiculo),
    FOREIGN KEY (fk_id_estacionamiento) REFERENCES estacionamientos(pk_id_estacionamiento),
    FOREIGN KEY (fk_id_espacio) REFERENCES espacios(pk_id_espacio)
);

-- Tabla administradores
CREATE TABLE administradores (
    pk_id_admin INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);
-- Datos iniciales
-- Usuarios
INSERT INTO usuarios (dni, codigo_estudiante, nombre, tipo_usuario, preferencia_estacionamiento, qr) VALUES
('12345678', 'C001', 'Juan Pérez', 'alumno', 1, 'QR001'),
('87654321', NULL, 'María López', 'profesor', 2, 'QR002');

-- Vehículos
INSERT INTO vehiculos (fk_id_usuario, placa, marca, modelo, color, anio) VALUES
(1, 'ABC123', 'Toyota', 'Corolla', 'Blanco', 2020),
(2, 'XYZ789', 'Honda', 'Civic', 'Negro', 2018);

-- Estacionamientos
INSERT INTO estacionamientos (nombre, tipo, capacidad) VALUES
('Estacionamiento General', 'general', 100),
('Estacionamiento Trabajadores', 'trabajadores', 50);

-- Espacios
INSERT INTO espacios (fk_id_estacionamiento, codigo_espacio, estado) VALUES
(1, 'G-001', 'disponible'),
(1, 'G-002', 'ocupado'),
(2, 'T-001', 'disponible');

-- Movimientos
INSERT INTO movimientos (fk_id_usuario, fk_id_vehiculo, fk_id_estacionamiento, fk_id_espacio, fecha_hora_entrada) VALUES
(1, 1, 1, 1, '2024-12-03 08:00:00'),
(2, 2, 2, 3, '2024-12-03 09:00:00');

-- Administradores
INSERT INTO administradores (nombre, usuario, contrasena) VALUES
('Administrador Principal', 'admin', 'password123');