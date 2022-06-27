INSERT INTO bancos (codigo,nombre,creado,actualizado) VALUES
	 ('BANK','BANK','2022-06-22 13:49:59','2022-06-22 13:49:59');
INSERT INTO clasificaciones_fiscales (codigo,nombre,creado,actualizado) VALUES
	 ('FISCAL C','FISCAL C','2022-06-22 13:50:24','2022-06-22 13:50:24');
INSERT INTO clasificaciones_impuestos (codigo,nombre,creado,actualizado) VALUES
	 ('TAX C','TAX C','2022-06-22 13:50:36','2022-06-22 13:50:36');
INSERT INTO grupos_retenciones_impuestos (codigo,nombre,creado,actualizado) VALUES
	 ('WIT','WIT','2022-06-22 13:50:50','2022-06-22 13:50:50');
INSERT INTO tipo_cuentas (codigo,nombre,creado,actualizado) VALUES
	 ('TIPO C','TIPO C','2022-06-22 13:51:02','2022-06-22 13:51:02');
INSERT INTO tipo_organizaciones_fiscales (codigo,nombre,creado,actualizado) VALUES
	 ('ORG F','ORG F','2022-06-22 13:51:15','2022-06-22 13:51:15');
INSERT INTO tipo_terceros (codigo,nombre,creado,actualizado) VALUES
	 ('TIPO T','TIPO T','2022-06-22 13:51:25','2022-06-22 13:51:25');
INSERT INTO tipos_clasificaciones_fiscales (codigo,nombre,creado,actualizado) VALUES
	 ('TIPO CL F','TIPO CL F','2022-06-22 13:51:39','2022-06-22 13:51:39');

INSERT INTO terceros (nombre,identificacion,id_contribuyente,tipo_tercero_id,tipo_organicacion_fiscal_id,banco_id,tipo_cuenta_id,grupo_retencion_impuestos_id,clasificacion_fiscal_id,tipo_clasificacion_fiscal_id,clasificacion_impuestos_id,numero_cuenta,estado,retencion_impuestos,creado,actualizado) VALUES
	 ('a','b','c',1,1,1,1,1,1,1,1,'111','ACTIVO','1','2022-06-22 14:28:31','2022-06-22 14:28:31');

INSERT INTO proyectos
(id, estado, codigo_grupo, nombre_grupo, codigo_consolidador, nombre_consolidador, codigo_proyecto, nombre_proyecto, banco_credito_constructor, tipo_financiacion, creado, actualizado)
VALUES(1, 'ACTIVO', '001', 'GRUPO AMARILO', '001', 'CONSOLIDADOR AMARILO', '001', 'PROYECTO NATURA', 'BBVA', 'CRÉDITO', '2022-06-23 17:09:00', '2022-06-27 10:31:50');
