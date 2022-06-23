CREATE TABLE IF NOT EXISTS `bancos` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `clasificaciones_fiscales` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `clasificaciones_fiscales` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `clasificaciones_impuestos` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `grupos_retenciones_impuestos` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
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
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `tasas` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`nombre` varchar(384) NOT NULL,
	`periodicidad` varchar(384) NOT NULL,
	`tipo_valor` varchar(384) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `tipos_clasificaciones_fiscales` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `tipo_cuentas` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `tipo_organizaciones_fiscales` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `tipo_terceros` (
	`id` int  NOT NULL AUTO_INCREMENT,
	`codigo` varchar(350) NOT NULL,
	`nombre` varchar(350) NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `terceros` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nombre` varchar(500) NOT NULL,
	`identificacion` varchar(50) NOT NULL,
	`id_contribuyente` varchar(50) NOT NULL,
	`tipo_tercero_id` int  NOT NULL,
	`tipo_organicacion_fiscal_id` int  NOT NULL,
	`banco_id` int  NOT NULL,
	`tipo_cuenta_id` int  NOT NULL,
	`grupo_retencion_impuestos_id` int  NOT NULL,
	`clasificacion_fiscal_id` int  NOT NULL,
	`tipo_clasificacion_fiscal_id` int  NOT NULL,
	`clasificacion_impuestos_id` int  NOT NULL,
	`numero_cuenta` varchar(50) DEFAULT NULL,
	`estado` varchar(50) DEFAULT NULL,
	`retencion_impuestos` varchar(1) DEFAULT NULL,
	`creado` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS `periodos` (
	`id` int NOT NULL AUTO_INCREMENT,
	`tasa_id` int  NOT NULL,
	`valor` varchar(50) NOT NULL,
	`fecha_inicio` date NOT NULL,
	`fecha_final` date NOT NULL,
	`creado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`actualizado` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
