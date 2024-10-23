
DROP DATABASE IF EXISTS proyecto_integrador;
CREATE DATABASE proyecto_integrador;

USE proyecto_integrador;


CREATE TABLE ubicacion (
    id_ubicacion INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL
);


INSERT INTO ubicacion (nombre) VALUES ('A1');
INSERT INTO ubicacion (nombre) VALUES ('A2');
INSERT INTO ubicacion (nombre) VALUES ('A3');


CREATE TABLE EstacionamientoAuto01 (
    idEstacionamientoAuto01 INT PRIMARY KEY AUTO_INCREMENT,
    idslot VARCHAR(45),
    placa VARCHAR(45), -- Cambiado de 'capacidad' a 'placa'
    ubicacion_id INT,
    CONSTRAINT fk_ubicacion_auto01 FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(id_ubicacion)
);


CREATE TABLE EstacionamientoMoto01 (
    idEstacionamientoMoto01 INT PRIMARY KEY AUTO_INCREMENT,
    idslot VARCHAR(45),
    placa VARCHAR(45), -- Cambiado de 'capacidad' a 'placa'
    ubicacion_id INT,
    CONSTRAINT fk_ubicacion_moto01 FOREIGN KEY (ubicacion_id) REFERENCES ubicacion(id_ubicacion)
);


INSERT INTO EstacionamientoAuto01 (idslot, placa, ubicacion_id) VALUES ('01', 'ABC123', (SELECT id_ubicacion FROM ubicacion WHERE nombre = 'A1'));


INSERT INTO EstacionamientoMoto01 (idslot, placa, ubicacion_id) VALUES ('47', 'XYZ789', (SELECT id_ubicacion FROM ubicacion WHERE nombre = 'A2'));

-- Consultas
SHOW TABLES;
SELECT * FROM EstacionamientoAuto01;
SELECT * FROM EstacionamientoMoto01;
SELECT * FROM ubicacion;
SELECT * FROM auditoria;





