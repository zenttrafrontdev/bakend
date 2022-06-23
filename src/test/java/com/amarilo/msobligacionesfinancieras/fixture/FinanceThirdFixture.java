package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;

import static com.amarilo.msobligacionesfinancieras.fixture.AccountTypeFixture.getAccountTypeEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.BankFixture.getBankEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.FinanceThirdTypeFixture.getFinanceThirdTypeEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.FiscalClassificationFixture.getFiscalClassificationEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.FiscalClassificationTypeFixture.getFiscalClassificationTypeEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.FiscalOrganizationTypeFixture.getFiscalOrganizationTypeEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.TaxClassificationFixture.getTaxClassificationEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.WithholdingTaxGroupFixture.getWithholdingTaxGroupEntity;

public class FinanceThirdFixture {
    public static FinanceThirdEntity getFinanceThirdEntity(){
        return FinanceThirdEntity.builder()
                .id(1)
                .name("Jesus")
                .identification("11")
                .contributorId("11")
                .accountNumber("1")
                .status("ACTIVO")
                .taxWithholding("Y")
                .financeThirdType(getFinanceThirdTypeEntity())
                .fiscalOrganizationType(getFiscalOrganizationTypeEntity())
                .bank(getBankEntity())
                .accountType(getAccountTypeEntity())
                .withholdingTaxGroup(getWithholdingTaxGroupEntity())
                .fiscalClassification(getFiscalClassificationEntity())
                .fiscalClassificationType(getFiscalClassificationTypeEntity())
                .taxClassification(getTaxClassificationEntity())
                .build();
    }
}
