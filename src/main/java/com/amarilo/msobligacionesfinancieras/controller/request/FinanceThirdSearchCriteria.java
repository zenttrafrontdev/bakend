package com.amarilo.msobligacionesfinancieras.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FinanceThirdSearchCriteria {
    private String name;
    private String identification;
    private String contributorId;
    private String accountNumber;
    private String status;
    private String taxWithholding;
    private Integer financeThirdTypeId;
    private Integer fiscalOrganizationTypeId;
    private Integer bankId;
    private Integer accountTypeId;
    private Integer withholdingTaxGroupId;
    private Integer fiscalClassificationId;
    private Integer fiscalClassificationTypeId;
    private Integer taxClassificationId;
}
