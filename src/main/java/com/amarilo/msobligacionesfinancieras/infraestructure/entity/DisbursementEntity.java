package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AmariloConceptEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.CreditTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DebtTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DisbursementOperationTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiduciaryConceptEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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
@Getter
@Setter
@Builder
@Entity
@Table(name = "desembolsos")
public class DisbursementEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_operacion_id", nullable = false)
    private DisbursementOperationTypeEntity disbursementOperationType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProjectEntity project;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cupo_id", nullable = false)
    private QuotaEntity quota;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_credito_id", nullable = false)
    private CreditTypeEntity creditType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_deuda_id", nullable = false)
    private DebtTypeEntity debtType;
    @Column(name = "valor")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "tercero_id", nullable = true)
    private FinanceThirdEntity financeThird;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "proyecto_tercero_id", nullable = true)
    private ProjectEntity projectFinanceThird;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private FinanceThirdEntity provider;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "concepto_amarilo_id", nullable = false)
    private AmariloConceptEntity amariloConcept;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "concepto_fiducia_id", nullable = false)
    private FiduciaryConceptEntity fiduciaryConcept;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proveedor_pago_id", nullable = false)
    private FinanceThirdEntity paymentProvider;
    @Column(name = "cuenta_destino")
    private String targetAccount;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_cuenta_id", nullable = false)
    private AccountTypeEntity accountType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "banco_destino_id", nullable = false)
    private BankEntity targetBank;
    @Column(name = "numero_factura_desembolso")
    private String disbursementInvoiceNumber;
    @Column(name = "egreso_desembolso")
    private String outlayDisbursement;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "banco_origen_id", nullable = false)
    private BankEntity sourceBank;
    @Column(name = "valor_gmf")
    private String gmfValue;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "representante_legal_id", nullable = false)
    private FinanceThirdEntity legalRepresentative;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "titular_id", nullable = false)
    private FinanceThirdEntity owner;
    @Column(name = "preoperativo")
    private boolean preoperative;
}
