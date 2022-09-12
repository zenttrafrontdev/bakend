create table if not exists bancos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists clasificaciones_fiscales (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists clasificaciones_fiscales (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists clasificaciones_impuestos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists grupos_retenciones_impuestos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists proyectos (
 id int unsigned not null auto_increment,
 estado varchar(384) not null,
 codigo_grupo varchar(384) not null,
 nombre_grupo varchar(384) not null,
 codigo_consolidador varchar(384) not null,
 nombre_consolidador varchar(384) not null,
 codigo_proyecto varchar(384) not null,
 nombre_proyecto varchar(384) not null,
 banco_credito_constructor varchar(384) default null,
 tipo_financiacion varchar(384) default null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id),
 unique key codigo_proyecto (codigo_proyecto)
);

create table if not exists tasas (
 id int unsigned not null auto_increment,
 nombre varchar(384) not null,
 periodicidad varchar(384) not null,
 tipo_valor varchar(384) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id)
);

create table if not exists tipos_clasificaciones_fiscales (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists tipo_cuentas (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists tipo_organizaciones_fiscales (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists tipo_terceros (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists unidades_negocio (
  id int unsigned not null auto_increment,
  sigla varchar(50) default null,
  razon varchar(50) default null,
  nombre varchar(50) default null,
  nit varchar(50) default null,
  estado varchar(50) default null,
  creado timestamp not null default current_timestamp,
  actualizado timestamp not null default current_timestamp on update current_timestamp,
  primary key (id)  using btree
);

create table if not exists tipologias_cupos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists clasificaciones_cupos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists periodicidad_intereses (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists tipos_credito (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists amortizaciones_capital (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists tipos_archivos_negocio (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree
);

create table if not exists fuentes_de_pago (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id)
);

create table if not exists tipos_de_pagos (
 id int unsigned not null auto_increment,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id)
);

CREATE TABLE if not exists conceptos_de_pago (
	id INT UNSIGNED auto_increment NOT NULL,
	codigo varchar(100) NULL,
	nombre varchar(100) NULL,
	creado timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	actualizado timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT conceptos_de_pago_pk PRIMARY KEY (id)
);


create table if not exists terceros (
 id int unsigned not null auto_increment,
 nombre varchar(500) not null,
 identificacion varchar(50) not null,
 id_contribuyente varchar(50) not null,
 tipo_tercero_id int unsigned not null,
 tipo_organicacion_fiscal_id int unsigned not null,
 banco_id int unsigned not null,
 tipo_cuenta_id int unsigned not null,
 grupo_retencion_impuestos_id int unsigned not null,
 clasificacion_fiscal_id int unsigned not null,
 tipo_clasificacion_fiscal_id int unsigned not null,
 clasificacion_impuestos_id int unsigned not null,
 numero_cuenta varchar(50) default null,
 estado varchar(50) default null,
 retencion_impuestos varchar(1) default null,
 creado timestamp null default current_timestamp,
 actualizado timestamp null default current_timestamp on update current_timestamp,
 primary key (id),
 key fk__tipo_tercero (tipo_tercero_id),
 key fk__tipo_organizacion_fiscal (tipo_organicacion_fiscal_id),
 key fk__bancos (banco_id),
 key fk__tipo_cuentas (tipo_cuenta_id),
 key fk__grupos_retenciones_impuestos (grupo_retencion_impuestos_id),
 key fk__clasificaciones_fiscales (clasificacion_fiscal_id),
 key fk__tipos_clasificaciones_fiscales (tipo_clasificacion_fiscal_id),
 key fk_terceros_codigo_clasificaciones_impuestos (clasificacion_impuestos_id),
 constraint fk__bancos foreign key (banco_id) references bancos (id) on delete restrict on update cascade,
 constraint fk__clasificaciones_fiscales foreign key (clasificacion_fiscal_id) references clasificaciones_fiscales (id) on delete restrict on update cascade,
 constraint fk__grupos_retenciones_impuestos foreign key (grupo_retencion_impuestos_id) references grupos_retenciones_impuestos (id) on delete restrict on update cascade,
 constraint fk__tipo_cuentas foreign key (tipo_cuenta_id) references tipo_cuentas (id) on delete restrict on update cascade,
 constraint fk__tipo_organizacion_fiscal foreign key (tipo_organicacion_fiscal_id) references tipo_organizaciones_fiscales (id) on delete restrict on update cascade,
 constraint fk__tipo_tercero foreign key (tipo_tercero_id) references tipo_terceros (id) on delete restrict on update cascade,
 constraint fk__tipos_clasificaciones_fiscales foreign key (tipo_clasificacion_fiscal_id) references tipos_clasificaciones_fiscales (id) on delete restrict on update cascade,
 constraint fk_terceros_clasificaciones_impuestos foreign key (clasificacion_impuestos_id) references clasificaciones_impuestos (id) on delete restrict on update cascade
);

create table if not exists periodos_tasas (
 id int unsigned not null auto_increment,
 tasa_id int unsigned not null,
 valor varchar(50) not null,
 fecha_inicio date not null,
 fecha_final date not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id),
 key fk__tasas (tasa_id),
 constraint fk__tasas foreign key (tasa_id) references tasas (id) on delete restrict on update cascade
);

create table if not exists terceros_financiadores (
 id int unsigned not null auto_increment,
 tercero_id INT UNSIGNED NULL,
 codigo varchar(350) not null,
 nombre varchar(350) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 CONSTRAINT terceros_financieros_pk PRIMARY KEY (id),
 CONSTRAINT terceros_financieros_tercero_FK FOREIGN KEY (tercero_id) REFERENCES terceros(id)
);

create table if not exists cupos (
 id int unsigned not null auto_increment,
 tipologia_cupo_id int unsigned not null,
 clasificacion_cupo_id int unsigned not null,
 unidad_negocio_id int unsigned not null,
 banco_id int unsigned not null,
 tipo_credito_id int unsigned not null,
 tasa_id int unsigned not null,
 fiduciaria_id int unsigned not null,
 cupo_aprobado varchar(350) not null,
 cupo_disponible varchar(350) not null,
 cupo_utilizado varchar(350) not null,
 cupo_disponible_constructor varchar(350) not null,
 fecha_aprobacion_cupo date not null,
 fecha_vencimiento_cupo date not null,
 plazo int not null,
 tercero_financiador_id int unsigned null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree,
 constraint fk_tipologia_cupo_id foreign key (tipologia_cupo_id) references tipologias_cupos (id) on delete restrict on update cascade,
 constraint fk_clasificacion_cupo_id foreign key (clasificacion_cupo_id) references clasificaciones_cupos (id) on delete restrict on update cascade,
 constraint fk_unidad_negocio_id foreign key (unidad_negocio_id) references unidades_negocio (id) on delete restrict on update cascade,
 constraint fk_banco_id foreign key (banco_id) references bancos (id) on delete restrict on update cascade,
 constraint fk_tipo_credito_id foreign key (tipo_credito_id) references tipos_credito (id) on delete restrict on update cascade,
 constraint fk_tasa_id foreign key (tasa_id) references tasas (id) on delete restrict on update cascade,
 constraint fk_fiduciaria_id foreign key (fiduciaria_id) references fiduciarias (id) on delete restrict on update cascade
 CONSTRAINT fk_tercero_financiador_id FOREIGN KEY (tercero_financiador_id) REFERENCES terceros_financiadores(id)
);

create table if not exists archivos_negocio (
 id int unsigned not null auto_increment,
 tipo_archivo_id int unsigned not null,
 nombre varchar(500) not null,
 descripcion varchar(500) not null,
 extension varchar(50) not null,
 tipo_contenido varchar(500) not null,
 tamano int not null,
 url varchar(1000) not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree,
 constraint fk_tipo_archivo_id foreign key (tipo_archivo_id) references tipos_archivos_negocio (id) on delete restrict on update cascade
);

create table if not exists archivos_negocio_cuotas (
	id int unsigned auto_increment not null,
	cupo_id int unsigned not null,
	archivo_negocio_id int unsigned not null,
	constraint archivos_negocio_cuotas_pk primary key (id),
	constraint fk_archivo_negocio foreign key (archivo_negocio_id) references archivos_negocio(id),
	constraint fk_cupo foreign key (cupo_id) references cupos(id)
);

CREATE TABLE if not exists detalle_cupos (
	id INT unsigned auto_increment NOT NULL,
	cupo_id INT unsigned NOT NULL,
	proyecto_id INT unsigned NOT NULL,
	 creado timestamp not null default current_timestamp,
     actualizado timestamp not null default current_timestamp on update current_timestamp,
	CONSTRAINT detalle_cupos_pk PRIMARY KEY (id),
	CONSTRAINT detalle_cupos_cupos_id_FK FOREIGN KEY (cupo_id) REFERENCES cupos(ID),
	CONSTRAINT detalle_cupos_proyecto_id_FK FOREIGN KEY (proyecto_id) REFERENCES proyectos(id)
);

CREATE TABLE if not exists tipo_operaciones_desembolsos (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  codigo varchar(350) NOT NULL,
  nombre varchar(350) NOT NULL,
  creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE if not exists tipo_deudas (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  codigo varchar(350) NOT NULL,
  nombre varchar(350) NOT NULL,
  creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE if not exists fiduciarias (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nit varchar(350) NOT NULL,
  nombre varchar(350) NOT NULL,
  creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

create table if not exists proyectos_fiduciarias (
	id int unsigned auto_increment not null,
	proyecto_id int unsigned not null,
	fiduciaria_id int unsigned not null,
	numero_obligacion varchar(350) NULL,
	anexo_titular varchar(350) NULL,
    creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	constraint proyectos_fiduciarias_pk primary key (id),
	constraint proyectos_fiduciarias_proyecto_fk foreign key (proyecto_id) references proyectos(id),
	constraint proyectos_fiduciarias_fiduciaria_fk foreign key (fiduciaria_id) references fiduciarias(id)
);

CREATE TABLE if not exists conceptos_fiduciaria (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  codigo varchar(350) NOT NULL,
  nombre varchar(350) NOT NULL,
  creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE if not exists conceptos_amarilo (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  codigo varchar(350) NOT NULL,
  nombre varchar(350) NOT NULL,
  creado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  actualizado timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE grupos_desembolso (
	id int auto_increment NOT NULL,
	consecutivo varchar(100) NULL,
	fecha DATE NOT NULL,
	proyecto_id int(10) unsigned NOT NULL,
	total_desembolso varchar(300) NOT NULL,
	total_gmf varchar(300) NOT NULL,
	otros varchar(300) NOT NULL,
	spread int NULL,
	vencimiento DATE NULL,
	numero_obligacion varchar(100) NULL,
	id_oracle varchar(100) NULL,
	periodicidad_interes_id int(10) unsigned NOT NULL,
	amortizacion_capital_id int(10) unsigned NOT NULL,
	carta_desembolso_impresa BIT DEFAULT 0 NOT NULL,
	fecha_efectiva DATE NULL,
	tasa_id int(10) unsigned NOT NULL,
	creado timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	actualizado timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT grupos_desembolso_pk PRIMARY KEY (id),
	CONSTRAINT grupos_desembolso_proyecto_FK FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),
	CONSTRAINT grupos_desembolso_tasa_FK FOREIGN KEY (tasa_id) REFERENCES tasas(id)
);


CREATE TABLE if not exists desembolsos (
	id INT UNSIGNED auto_increment NOT NULL,
	tipo_operacion_id INT UNSIGNED NOT NULL,
	grupo_desembolso_id INT UNSIGNED NOT NULL,
	proyecto_id INT UNSIGNED NOT NULL,
	cupo_id INT UNSIGNED NOT NULL,
	tipo_credito_id INT UNSIGNED NOT NULL,
	tipo_deuda_id INT UNSIGNED NOT NULL,
	valor varchar(100) NOT NULL,
	tercero_id INT UNSIGNED NULL,
	proyecto_tercero_id INT UNSIGNED NULL,
	proveedor_id INT UNSIGNED NOT NULL,
	concepto_amarilo_id INT UNSIGNED NOT NULL,
	concepto_fiducia_id INT UNSIGNED NOT NULL,
	proveedor_pago_id INT UNSIGNED NOT NULL,
	cuenta_destino varchar(100) NOT NULL,
	tipo_cuenta_id INT UNSIGNED NOT NULL,
	numero_factura_desembolso varchar(250) NOT NULL,
	egreso_desembolso varchar(100) NULL,
	valor_gmf varchar(100) NOT NULL,
	representante_legal_id INT UNSIGNED NOT NULL,
	titular_id INT UNSIGNED NOT NULL,
	preoperativo BIT DEFAULT 0 NOT NULL,
	creado timestamp not null default current_timestamp,
	actualizado timestamp not null default current_timestamp on update current_timestamp,
	CONSTRAINT desembolsos_pk PRIMARY KEY (id),
	CONSTRAINT desembolsos_tipo_operacion_FK FOREIGN KEY (tipo_operacion_id) REFERENCES tipo_operaciones_desembolsos(id),
	CONSTRAINT desembolsos_proyecto_FK FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),
	CONSTRAINT desembolsos_cupo_FK FOREIGN KEY (cupo_id) REFERENCES cupos(id),
	CONSTRAINT desembolsos_tipo_credito_FK FOREIGN KEY (tipo_credito_id) REFERENCES tipos_credito(ID),
	CONSTRAINT desembolsos_tipo_deuda_FK FOREIGN KEY (tipo_deuda_id) REFERENCES tipo_deudas(id),
	CONSTRAINT desembolsos_tercero_FK FOREIGN KEY (tercero_id) REFERENCES terceros(id),
	CONSTRAINT desembolsos_proyecto_tercero_FK FOREIGN KEY (proyecto_tercero_id) REFERENCES proyectos(id),
	CONSTRAINT desembolsos_proveedor_FK FOREIGN KEY (proveedor_id) REFERENCES terceros(id),
	CONSTRAINT desembolsos_proveedor_pago_FK FOREIGN KEY (proveedor_pago_id) REFERENCES terceros(id),
	CONSTRAINT desembolsos_tipo_cuenta_FK FOREIGN KEY (tipo_cuenta_id) REFERENCES tipo_cuentas(id),
	CONSTRAINT desembolsos_representante_legal_FK FOREIGN KEY (representante_legal_id) REFERENCES terceros(id),
	CONSTRAINT desembolsos_titular_FK FOREIGN KEY (titular_id) REFERENCES terceros(id),
	CONSTRAINT desembolsos_conceptos_fiducia_FK FOREIGN KEY (concepto_fiducia_id) REFERENCES conceptos_fiduciaria(id),
	CONSTRAINT desembolsos_conceptos_amarilo_FK FOREIGN KEY (concepto_amarilo_id) REFERENCES conceptos_amarilo(id),
	CONSTRAINT desembolsos_grupos_desembolso_FK FOREIGN KEY (grupo_desembolso_id) REFERENCES grupos_desembolso(id)
);

CREATE TABLE if not exists fiduciaria_proyecto (
	id INT auto_increment NOT NULL,
	fiduciaria_id int(10) unsigned NOT NULL,
	proyecto_id int(10) unsigned NOT NULL,
	numero_obligacion varchar(100) NULL,
	nit_banco varchar(100) NULL,
	nombre_titular varchar(100) NULL,
	anexo_titular varchar(100) NULL,
	CONSTRAINT fiduciaria_proyecto_pk PRIMARY KEY (id),
	CONSTRAINT fiduciaria_proyecto_proyecto_FK FOREIGN KEY (proyecto_id) REFERENCES proyectos(id),
    CONSTRAINT fiduciaria_proyecto_fiduciaria_FK FOREIGN KEY (fiduciaria_id) REFERENCES fiduciarias(id)
);

CREATE TABLE if not exists detalle_pagos_otros_conceptos (
	id INT UNSIGNED auto_increment NOT NULL,
	concepto_pago_id INT UNSIGNED NOT NULL,
	pago_id INT UNSIGNED NOT NULL,
	valor varchar(350) NOT NULL,
	creado timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
	actualizado timestamp DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL,
	CONSTRAINT detalle_pagos_otros_conceptos_pk PRIMARY KEY (id),
	CONSTRAINT detalle_pagos_otros_conceptos_conceptos_pago_FK FOREIGN KEY (concepto_pago_id) REFERENCES conceptos_de_pago(id),
	CONSTRAINT detalle_pagos_otros_conceptos_pagos_FK FOREIGN KEY (pago_id) REFERENCES pagos(id)
);

CREATE TABLE if not exists pagos (
	id INT auto_increment NOT NULL,
	grupo_desembolso_id int(10) NOT NULL,
	tipo_pago_id int(10) unsigned NOT NULL,
	fuente_pago_id int(10) unsigned NOT NULL,
	fecha DATE  NOT NULL,
	fecha_aplicada DATE NULL,
	capital varchar(350) NOT NULL,
	intereses varchar(350) NOT NULL,
	valor varchar(350) NOT NULL,
	valor_total varchar(350) NOT NULL,
	id_oracle varchar(100) NULL,
    creado timestamp not null default current_timestamp,
    actualizado timestamp not null default current_timestamp on update current_timestamp,
	CONSTRAINT pagos_pk PRIMARY KEY (id),
    CONSTRAINT pagos_grupos_desembolso_FK FOREIGN KEY (grupo_desembolso_id) REFERENCES grupos_desembolso(id),
    CONSTRAINT pagos_tipo_pago_FK FOREIGN KEY (tipo_pago_id) REFERENCES tipos_de_pagos(id),
    CONSTRAINT pagos_fuente_pago_FK FOREIGN KEY (fuente_pago_id) REFERENCES fuentes_de_pago(id)
);