CREATE TABLE IF NOT EXISTS `bancos` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `clasificaciones_fiscales` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `clasificaciones_fiscales` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `clasificaciones_impuestos` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `grupos_retenciones_impuestos` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `proyectos` (
 `id` int NOT NULL AUTO_INCREMENT,
 `estado` varchar(384) NOT NULL,
 `codigo_grupo` varchar(384) NOT NULL,
 `nombre_grupo` varchar(384) NOT NULL,
 `codigo_consolidador` varchar(384) NOT NULL,
 `nombre_consolidador` varchar(384) NOT NULL,
 `codigo_proyecto` varchar(384) NOT NULL,
 `nombre_proyecto` varchar(384) NOT NULL,
 `banco_credito_constructor` varchar(384) DEFAULT NULL,
 `tipo_financiacion` varchar(384) DEFAULT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 UNIQUE KEY `codigo_proyecto` (`codigo_proyecto`)
);

CREATE TABLE IF NOT EXISTS `tasas` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `nombre` varchar(384) NOT NULL,
 `periodicidad` varchar(384) NOT NULL,
 `tipo_valor` varchar(384) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tipos_clasificaciones_fiscales` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `tipo_cuentas` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `tipo_organizaciones_fiscales` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `tipo_terceros` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `codigo` varchar(350) NOT NULL,
 `nombre` varchar(350) NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `terceros` (
 `id` int NOT NULL AUTO_INCREMENT,
 `nombre` varchar(500) NOT NULL,
 `identificacion` varchar(50) NOT NULL,
 `id_contribuyente` varchar(50) NOT NULL,
 `tipo_tercero_id` int unsigned NOT NULL,
 `tipo_organicacion_fiscal_id` int unsigned NOT NULL,
 `banco_id` int unsigned NOT NULL,
 `tipo_cuenta_id` int unsigned NOT NULL,
 `grupo_retencion_impuestos_id` int unsigned NOT NULL,
 `clasificacion_fiscal_id` int unsigned NOT NULL,
 `tipo_clasificacion_fiscal_id` int unsigned NOT NULL,
 `clasificacion_impuestos_id` int unsigned NOT NULL,
 `numero_cuenta` varchar(50) DEFAULT NULL,
 `estado` varchar(50) DEFAULT NULL,
 `retencion_impuestos` varchar(1) DEFAULT NULL,
 `creado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `FK__tipo_tercero` (`tipo_tercero_id`),
 KEY `FK__tipo_organizacion_fiscal` (`tipo_organicacion_fiscal_id`),
 KEY `FK__bancos` (`banco_id`),
 KEY `FK__tipo_cuentas` (`tipo_cuenta_id`),
 KEY `FK__grupos_retenciones_impuestos` (`grupo_retencion_impuestos_id`),
 KEY `FK__clasificaciones_fiscales` (`clasificacion_fiscal_id`),
 KEY `FK__tipos_clasificaciones_fiscales` (`tipo_clasificacion_fiscal_id`),
 KEY `FK_terceros_codigo_clasificaciones_impuestos` (`clasificacion_impuestos_id`),
 CONSTRAINT `FK__bancos` FOREIGN KEY (`banco_id`) REFERENCES `bancos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__clasificaciones_fiscales` FOREIGN KEY (`clasificacion_fiscal_id`) REFERENCES `clasificaciones_fiscales` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__grupos_retenciones_impuestos` FOREIGN KEY (`grupo_retencion_impuestos_id`) REFERENCES `grupos_retenciones_impuestos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__tipo_cuentas` FOREIGN KEY (`tipo_cuenta_id`) REFERENCES `tipo_cuentas` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__tipo_organizacion_fiscal` FOREIGN KEY (`tipo_organicacion_fiscal_id`) REFERENCES `tipo_organizaciones_fiscales` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__tipo_tercero` FOREIGN KEY (`tipo_tercero_id`) REFERENCES `tipo_terceros` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK__tipos_clasificaciones_fiscales` FOREIGN KEY (`tipo_clasificacion_fiscal_id`) REFERENCES `tipos_clasificaciones_fiscales` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
 CONSTRAINT `FK_terceros_clasificaciones_impuestos` FOREIGN KEY (`clasificacion_impuestos_id`) REFERENCES `clasificaciones_impuestos` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `periodos` (
 `id` int NOT NULL AUTO_INCREMENT,
 `tasa_id` int unsigned NOT NULL,
 `valor` varchar(50) NOT NULL,
 `fecha_inicio` date NOT NULL,
 `fecha_final` date NOT NULL,
 `creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
 `actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 PRIMARY KEY (`id`),
 KEY `FK__tasas` (`tasa_id`),
 CONSTRAINT `FK__tasas` FOREIGN KEY (`tasa_id`) REFERENCES `tasas` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);