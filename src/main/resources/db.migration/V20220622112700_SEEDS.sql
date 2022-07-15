INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(1, 'UVR', '1', '1');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(2, 'DTF EA', '2', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(3, 'DTF TA', '2', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(4, 'IBR 1M', '1', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(5, 'IBR 3M', '1', '3');

INSERT INTO tipo_cuentas (id, codigo, nombre) VALUES(1, 'AHORROS', 'AHORROS');
INSERT INTO tipo_cuentas (id, codigo, nombre, creado, actualizado) VALUES(2, 'CORRIENTE', 'CORRIENTE');

INSERT INTO tipo_organizaciones_fiscales (id, codigo, nombre) VALUES(1, 'Persona Juridica', 'Persona Juridica');
INSERT INTO tipo_organizaciones_fiscales (id, codigo, nombre, creado, actualizado) VALUES(2, 'Persona Natural', 'Persona Natural');

INSERT INTO tipologias_cupos (CODIGO, NOMBRE) VALUES('001', 'Rotativo');
INSERT INTO tipologias_cupos (CODIGO, NOMBRE) VALUES('002', 'No Rotativo');
INSERT INTO tipologias_cupos (CODIGO, NOMBRE) VALUES('003', 'Proyectos');

INSERT INTO tipos_credito (CODIGO, NOMBRE) VALUES('001', 'Constructor');
INSERT INTO tipos_credito (CODIGO, NOMBRE) VALUES('002', 'Tesoreria');
INSERT INTO tipos_credito (CODIGO, NOMBRE) VALUES('003', 'Leasing');
INSERT INTO tipos_credito (CODIGO, NOMBRE) VALUES('004', 'Terceros');

INSERT INTO clasificaciones_cupos (CODIGO, NOMBRE) VALUES('001', 'Constructor');
INSERT INTO clasificaciones_cupos (CODIGO, NOMBRE) VALUES('002', 'Tesoreria');
INSERT INTO clasificaciones_cupos (CODIGO, NOMBRE) VALUES('003', 'Leasing');
INSERT INTO clasificaciones_cupos (CODIGO, NOMBRE) VALUES('004', 'Terceros');

INSERT INTO amortizaciones_capital (CODIGO, NOMBRE) VALUES('001', 'Mensual');
INSERT INTO amortizaciones_capital (CODIGO, NOMBRE) VALUES('002', 'Trimestral');
INSERT INTO amortizaciones_capital (CODIGO, NOMBRE) VALUES('003', 'Semestral');
INSERT INTO amortizaciones_capital (CODIGO, NOMBRE) VALUES('004', 'Anual');
INSERT INTO amortizaciones_capital (CODIGO, NOMBRE) VALUES('005', 'Al vencimiento');

INSERT INTO periodicidad_intereses (CODIGO, NOMBRE) VALUES('001', 'Mensual');
INSERT INTO periodicidad_intereses (CODIGO, NOMBRE) VALUES('002', 'Trimestral');
INSERT INTO periodicidad_intereses (CODIGO, NOMBRE) VALUES('003', 'Al vencimiento');

