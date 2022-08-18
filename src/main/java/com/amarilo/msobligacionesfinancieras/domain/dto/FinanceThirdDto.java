package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FinanceThirdDto {
    private Integer id;
    private String name;
    private String identification;
    private String contributorId;
    private String accountNumber;
    private String status;
    private String taxWithholding;
    private GenericMasterDto financeThirdType;
    private GenericMasterDto fiscalOrganizationType;
    private GenericMasterDto bank;
    private GenericMasterDto accountType;
    private GenericMasterDto withholdingTaxGroup;
    private GenericMasterDto fiscalClassification;
    private GenericMasterDto fiscalClassificationType;
    private GenericMasterDto taxClassification;
}
