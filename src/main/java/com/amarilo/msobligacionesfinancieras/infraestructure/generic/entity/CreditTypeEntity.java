package com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@Entity
@Table(name = "tipos_credito")
public class CreditTypeEntity extends GenericMasterEntity {
}
