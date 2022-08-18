package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BusinessAreaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.CapitalAmortizationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.CreditTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PeriodicityInterestEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.QuotaTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cupos")
public class QuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipologia_cupo_id", nullable = false)
    private QuotaTypeEntity quotaType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clasificacion_cupo_id", nullable = false)
    private QuotaTypeEntity quotaClassification;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unidad_negocio_id", nullable = false)
    private BusinessAreaEntity businessArea;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cupo_id", nullable = false)
    private List<QuotaDetailEntity> quotaDetails;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "banco_id", nullable = false)
    private BankEntity bank;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_credito_id", nullable = false)
    private CreditTypeEntity creditType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "amortizacion_capital_id", nullable = false)
    private CapitalAmortizationEntity capitalAmortization;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodicidad_interes_id", nullable = false)
    private PeriodicityInterestEntity periodicityInterest;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fee_id", nullable = false)
    private FeeEntity fee;
    @Column(name = "cupo_aprobado")
    private String approvedQuota;
    @Column(name = "cupo_disponible")
    private String availableQuota;
    @Column(name = "cupo_utilizado")
    private String quotaUsed;
    @Column(name = "cupo_disponible_constructor")
    private String builderAvailableQuota;
    @Column(name = "fecha_aprobacion_cupo")
    private LocalDate approvedQuotaDate;
    @Column(name = "fecha_vencimiento_cupo")
    private LocalDate expirationQuotaDate;
    @Column(name = "plazo")
    private Integer deadLine;
}