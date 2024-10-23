
INSERT INTO EstacionamientoAuto01 (idslot, placa, ubicacion_id) VALUES ('01', 'ABC123', (SELECT id_ubicacion FROM ubicacion WHERE nombre = 'A1'));

INSERT INTO EstacionamientoMoto01 (idslot, placa, ubicacion_id) VALUES ('47', 'XYZ789', (SELECT id_ubicacion FROM ubicacion WHERE nombre = 'A2'));
