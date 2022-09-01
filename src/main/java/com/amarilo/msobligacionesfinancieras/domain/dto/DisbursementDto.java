package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementDto {
    private Integer id;
    private GenericMasterDto disbursementOperationType;
    private ProjectDto project;
    private QuotaDto quota;
    private GenericMasterDto creditType;
    private GenericMasterDto debtType;
    private String value;
    private FinanceThirdDto financeThird;
    private ProjectDto projectFinanceThird;
    private FinanceThirdDto provider;
    private GenericMasterDto amariloConcept;
    private GenericMasterDto fiduciaryConcept;
    private FinanceThirdDto paymentProvider;
    private String targetAccount;
    private GenericMasterDto accountType;
    private String disbursementInvoiceNumber;
    private String outlayDisbursement;
    private String gmfValue;
    private FinanceThirdDto legalRepresentative;
    private FinanceThirdDto owner;
    private boolean preoperative;
}
