package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.CreditTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DebtTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DisbursementOperationTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "desembolsos")
public class DisbursementEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "consecutivo")
    private Integer consecutive;
    @Column(name = "fecha")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_operacion_id", nullable = false)
    private DisbursementOperationTypeEntity disbursementOperationType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProjectEntity project;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_credito_id", nullable = false)
    private CreditTypeEntity creditType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_deuda_id", nullable = false)
    private DebtTypeEntity debtType;
    @Column(name = "valor")
    private String value;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tercero_id", nullable = false)
    private FinanceThirdEntity financeThird;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private FinanceThirdEntity supplier;
    @Column(name = "concepto_amarilo")
    private String amariloConcept;
    @Column(name = "concepto_fiducia")
    private String fiduciaryConcept;
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
    @Column(name = "fiduciaria")
    private String fiduciary;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "representante_legal_id", nullable = false)
    private FinanceThirdEntity legalRepresentative;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "titular_id", nullable = false)
    private FinanceThirdEntity owner;
    @Column(name = "preoperativo")
    private String preoperative;
}
