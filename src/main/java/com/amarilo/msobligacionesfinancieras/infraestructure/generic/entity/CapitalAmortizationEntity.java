package com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "amortizaciones_capital")
public class CapitalAmortizationEntity extends GenericMasterEntity {
}
