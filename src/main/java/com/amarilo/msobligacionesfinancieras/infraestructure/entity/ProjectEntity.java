package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "proyectos")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_proyecto")
    private String projectCode;
    @Column(name = "nombre_proyecto")
    private String projectName;
    @Column(name = "codigo_grupo")
    private String groupCode;
    @Column(name = "nombre_grupo")
    private String groupName;
    @Column(name = "codigo_consolidador")
    private String consolidatorCode;
    @Column(name = "nombre_consolidador")
    private String consolidatorName;
    @Column(name = "banco_credito_constructor")
    private String buildersCreditBank;
    @Column(name = "tipo_financiacion")
    private String paymentType;
    @Column(name = "estado")
    private String status;
}
