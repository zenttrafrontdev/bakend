package com.amarilo.msobligacionesfinancieras.infraestructure.entity;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalClassificationTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiscalOrganizationTypeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.TaxClassificationEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.WithholdingTaxGroupEntity;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "terceros")
public class FinanceThirdEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "identificacion")
    private String identification;
    @Column(name = "id_contribuyente")
    private String contributorId;
    @Column(name = "numero_cuenta")
    private String accountNumber;
    @Column(name = "estado")
    private String status;
    @Column(name = "retencion_impuestos")
    private String taxWithholding;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_tercero_id", nullable = false)
    private FinanceThirdTypeEntity financeThirdType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_organicacion_fiscal_id", nullable = false)
    private FiscalOrganizationTypeEntity fiscalOrganizationType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "banco_id", nullable = false)
    private BankEntity bank;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_cuenta_id", nullable = false)
    private AccountTypeEntity accountType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grupo_retencion_impuestos_id", nullable = false)
    private WithholdingTaxGroupEntity withholdingTaxGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clasificacion_fiscal_id", nullable = false)
    private FiscalClassificationEntity fiscalClassification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_clasificacion_fiscal_id", nullable = false)
    private FiscalClassificationTypeEntity fiscalClassificationType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clasificacion_impuestos_id", nullable = false)
    private TaxClassificationEntity taxClassification;
}
