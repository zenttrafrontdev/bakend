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

create table if not exists terceros (
 id int not null auto_increment,
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
 id int not null auto_increment,
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

create table if not exists cupos (
 id int unsigned not null auto_increment,
 tipologia_cupo_id int unsigned not null,
 clasificacion_cupo_id int unsigned not null,
 unidad_negocio_id int unsigned not null,
 banco_id int unsigned not null,
 tipo_credito_id int unsigned not null,
 amortizacion_capital_id int unsigned not null,
 periodicidad_interes_id int unsigned not null,
 fee_id int unsigned not null,
 cupo_aprobado varchar(350) not null,
 cupo_disponible varchar(350) not null,
 cupo_utilizado varchar(350) not null,
 cupo_disponible_constructor varchar(350) not null,
 fecha_aprobacion_cupo date not null,
 fecha_vencimiento_cupo date not null,
 plazo int not null,
 creado timestamp not null default current_timestamp,
 actualizado timestamp not null default current_timestamp on update current_timestamp,
 primary key (id) using btree,
 constraint fk_tipologia_cupo_id foreign key (tipologia_cupo_id) references tipologias_cupos (id) on delete restrict on update cascade,
 constraint fk_clasificacion_cupo_id foreign key (clasificacion_cupo_id) references clasificaciones_cupos (id) on delete restrict on update cascade,
 constraint fk_unidad_negocio_id foreign key (unidad_negocio_id) references unidades_negocio (id) on delete restrict on update cascade,
 constraint fk_banco_id foreign key (banco_id) references bancos (id) on delete restrict on update cascade,
 constraint fk_tipo_credito_id foreign key (tipo_credito_id) references tipos_credito (id) on delete restrict on update cascade,
 constraint fk_amortizacion_capital_id foreign key (amortizacion_capital_id) references amortizaciones_capital (id) on delete restrict on update cascade,
 constraint fk_periodicidad_interes_id foreign key (periodicidad_interes_id) references periodicidad_intereses (id) on delete restrict on update cascade,
 constraint fk_fee_id foreign key (fee_id) references tasas (id) on delete restrict on update cascade
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

create table archivos_negocio_cuotas (
	id int unsigned auto_increment not null,
	cupo_id int unsigned not null,
	archivo_negocio_id int unsigned not null,
	constraint archivos_negocio_cuotas_pk primary key (id),
	constraint fk_archivo_negocio foreign key (archivo_negocio_id) references archivos_negocio(id),
	constraint fk_cupo foreign key (cupo_id) references cupos(id)
);

CREATE TABLE detalle_cupos (
	id INT auto_increment NOT NULL,
	cupo_id INT unsigned NOT NULL,
	proyecto_id INT unsigned NOT NULL,
	 creado timestamp not null default current_timestamp,
     actualizado timestamp not null default current_timestamp on update current_timestamp,
	CONSTRAINT detalle_cupos_pk PRIMARY KEY (id),
	CONSTRAINT detalle_cupos_cupos_id_FK FOREIGN KEY (cupo_id) REFERENCES cupos(ID),
	CONSTRAINT detalle_cupos_proyecto_id_FK FOREIGN KEY (proyecto_id) REFERENCES proyectos(id)
);
