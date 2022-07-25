package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DisbursementDto {
    private Integer id;
    private Integer consecutive;
    private LocalDate date;
    private GenericMasterDto disbursementOperationType;
    private ProjectDto project;
    private GenericMasterDto creditType;
    private GenericMasterDto debtType;
    private String value;
    private FinanceThirdDto financeThird;
    private FinanceThirdDto supplier;
    private String amariloConcept;
    private String fiduciaryConcept;
    private FinanceThirdDto paymentProvider;
    private String targetAccount;
    private GenericMasterDto accountType;
    private GenericMasterDto targetBank;
    private String disbursementInvoiceNumber;
    private String outlayDisbursement;
    private GenericMasterDto sourceBank;
    private String gmfValue;
    private String fiduciary;
    private FinanceThirdDto legalRepresentative;
    private FinanceThirdDto owner;
    private String preoperative;
}
