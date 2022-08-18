package com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "unidades_negocio")
public class BusinessAreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sigla")
    private String acronym;
    @Column(name = "razon")
    private String corporateName;
    @Column(name = "nombre")
    private String name;
    @Column(name = "nit")
    private String nit;
    @Column(name = "estado")
    private String status;
}
