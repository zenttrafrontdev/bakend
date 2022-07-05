INSERT INTO tasas
(id, nombre, periodicidad, tipo_valor)
VALUES(1, 'UVR', '1', '1');
INSERT INTO tasas
(id, nombre, periodicidad, tipo_valor)
VALUES(2, 'DTF EA', '2', '3');
INSERT INTO tasas
(id, nombre, periodicidad, tipo_valor)
VALUES(3, 'DTF TA', '2', '3');
INSERT INTO tasas
(id, nombre, periodicidad, tipo_valor)
VALUES(4, 'IBR 1M', '1', '3');
INSERT INTO tasas
(id, nombre, periodicidad, tipo_valor)
VALUES(5, 'IBR 3M', '1', '3');

INSERT INTO tipo_cuentas
(id, codigo, nombre)
VALUES(1, 'AHORROS', 'AHORROS');
INSERT INTO tipo_cuentas
(id, codigo, nombre, creado, actualizado)
VALUES(2, 'CORRIENTE', 'CORRIENTE');

INSERT INTO tipo_organizaciones_fiscales
(id, codigo, nombre)
VALUES(1, 'Persona Juridica', 'Persona Juridica');
INSERT INTO tipo_organizaciones_fiscales
(id, codigo, nombre, creado, actualizado)
VALUES(2, 'Persona Natural', 'Persona Natural');

