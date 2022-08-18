package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementDto {
    private Integer id;
    private Integer consecutive;
    private LocalDate date;
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
    private GenericMasterDto targetBank;
    private String disbursementInvoiceNumber;
    private String outlayDisbursement;
    private GenericMasterDto sourceBank;
    private String gmfValue;
    private FiduciaryDto fiduciary;
    private FinanceThirdDto legalRepresentative;
    private FinanceThirdDto owner;
    private String preoperative;
}
