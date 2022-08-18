INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(1, 'UVR', '1', '1');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(2, 'DTF EA', '2', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(3, 'DTF TA', '2', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(4, 'IBR 1M', '1', '3');
INSERT INTO tasas (id, nombre, periodicidad, tipo_valor) VALUES(5, 'IBR 3M', '1', '3');

INSERT INTO tipo_cuentas (id, codigo, nombre) VALUES(1, 'AHORROS', 'AHORROS');
INSERT INTO tipo_cuentas (id, codigo, nombre) VALUES(2, 'CORRIENTE', 'CORRIENTE');

INSERT INTO tipo_organizaciones_fiscales (id, codigo, nombre) VALUES(1, 'Persona Juridica', 'Persona Juridica');
INSERT INTO tipo_organizaciones_fiscales (id, codigo, nombre) VALUES(2, 'Persona Natural', 'Persona Natural');

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

INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('LOTE', 'LOTE');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('DEUDA', 'DEUDA');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('PRESTAMO', 'PRESTAMO');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('APORTE', 'APORTE');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('DEV.APORTE', 'DEV.APORTE');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('DEV.PRESTAMO', 'DEV.PRESTAMO');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('BENEFICIOS', 'BENEFICIOS');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('UTILIDADES', 'UTILIDADES');
INSERT INTO tipo_operaciones_desembolsos (codigo, nombre) VALUES('ANTICIPO_OBRA', 'ANTICIPO_OBRA');

INSERT INTO tipo_deudas (codigo, nombre) VALUES('CORPORATIVA', 'CORPORATIVA', '2022-07-25 21:37:55', '2022-07-25 21:37:55');
INSERT INTO tipo_deudas (codigo, nombre) VALUES('PROYECTOS', 'PROYECTOS');

INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('001', 'CAPITAL_PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('002', 'CAPITAL_PRORRATA_PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('003', 'PAGO LOTE COMPRAVENTA PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('004', 'PRESTAMOS');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('005', 'ANTICIPO OBRA SOCIOS PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('006', 'APORTE EN DINERO CON ABONO AL PROYECTO');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('007', 'DEVOLUCION APORTES LOTE AMARILO');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('008', 'DEVOLUCION APORTES LOTE INVERSIONISTAS PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('009', 'DEVOLUCION APORTES LOTE SOCIOS PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('010', 'DEVOLUCION APORTES AMARILO');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('011', 'DEVOLUCION DE PRESTAMOS');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('012', 'ANTICIPO BENEFICIOS AMARILO');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('013', 'ANTICIPO UTILIDADES AMARILO');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('014', 'ANTICIPO UTILIDADES SOCIOS PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('015', 'ANTICIPO UTILIDADES LOTE INVERSIONISTAS PA');
INSERT INTO conceptos_amarilo (codigo, nombre) VALUES('016', 'ANTICIPO OBRA AMARILO');

INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('001', 'PAGO OBLIGACIÓN');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('002', 'PAGO PRORRATAS');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('003', 'PAGO LOTE');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('004', 'ANTICIPO DE OBRA');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('005', 'NA');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('006', 'RESTITUCIÓN APORTES LOTE');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('007', 'RESTITUCIÓN APORTES');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('008', 'ANTICIPO BENEFICIOS');
INSERT INTO conceptos_fiduciaria (codigo, nombre) VALUES('009', 'ANTICIPO UTILIDADES');
