package com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipologias_cupos")
public class QuotaTypeEntity extends GenericMasterEntity {
}
