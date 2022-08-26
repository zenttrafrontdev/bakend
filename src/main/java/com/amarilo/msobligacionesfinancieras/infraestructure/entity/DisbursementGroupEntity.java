package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.CapitalAmortizationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PeriodicityInterestEntity;
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
@Table(name = "grupos_desembolso")
public class DisbursementGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "consecutivo")
    private Integer consecutive;
    @Column(name = "fecha")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProjectEntity project;
    @Column(name = "total_desembolso")
    private String totalDisbursement;
    @Column(name = "total_gmf")
    private String totalGmf;
    @Column(name = "otros")
    private String others;
    private Integer spread;
    @Column(name = "vencimiento")
    private LocalDate expirationDate;
    @Column(name = "numero_obligacion")
    private String obligationNumber;
    @Column(name = "id_oracle")
    private String idOracle;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periodicidad_interes_id", nullable = false)
    private PeriodicityInterestEntity periodicityInterest;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "amortizacion_capital_id", nullable = false)
    private CapitalAmortizationEntity capitalAmortization;
    @Column(name = "carta_desembolso_impresa")
    private boolean disbursementLetterPrinted;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "grupo_desembolso_id", nullable = false)
    private List<DisbursementEntity> disbursementList;
}
