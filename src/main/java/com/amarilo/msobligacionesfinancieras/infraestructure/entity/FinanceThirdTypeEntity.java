package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.GenericMasterEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@Entity
@Table(name = "tipo_terceros")
public class FinanceThirdTypeEntity extends GenericMasterEntity {
}
