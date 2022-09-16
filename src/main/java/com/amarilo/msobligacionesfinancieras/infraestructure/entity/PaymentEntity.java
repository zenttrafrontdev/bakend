package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentConceptEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentSourceEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentTypeEntity;
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
@Table(name = "pagos")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha")
    private LocalDate date;
    @Column(name = "capital")
    private String capital;
    @Column(name = "valor")
    private String value;
    @Column(name = "fecha_aplicada")
    private LocalDate appliedDate;
    @Column(name = "id_oracle")
    private String oracleId;
    @Column(name = "intereses")
    private String rates;
    @Column(name = "valor_total")
    private String totalValue;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupo_desembolso_id", nullable = false)
    private DisbursementGroupEntity disbursementGroup;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fuente_pago_id", nullable = false)
    private PaymentSourceEntity paymentSource;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_pago_id", nullable = false)
    private PaymentTypeEntity paymentType;
    @Column(name = "comentarios")
    private String comments;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pago_id", nullable = false)
    private List<PaymentDetailOtherConceptEntity> paymentDetailOtherConcepts;
}
