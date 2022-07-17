package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.QuotaTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "archivos_negocio_cuotas")
public class QuotaFileBusinessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cupo_id", nullable = false, updatable = false)
    private QuotaEntity quota;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "archivo_negocio_id", nullable = false, updatable = false)
    private FileBusinessEntity fileBusiness;
}
