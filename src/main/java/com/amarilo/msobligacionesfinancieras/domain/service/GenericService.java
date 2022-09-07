package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.domain.dto.BusinessAreaDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;

import java.util.List;

public interface GenericService {
    List<GenericMasterDto> findAllFinanceThirdTypes();

    List<GenericMasterDto> findAllFiscalOrganizationTypes();

    List<GenericMasterDto> findAllBanks();

    List<GenericMasterDto> findAllAccountTypes();

    List<GenericMasterDto> findAllWithholdingTaxGroups();

    List<GenericMasterDto> findAllFiscalClassifications();

    List<GenericMasterDto> findAllFiscalClassificationTypes();

    List<GenericMasterDto> findAllTaxClassifications();

    List<BusinessAreaDto> findAllBusinessAreas();

    List<GenericMasterDto> findAllQuotaTypes();

    List<GenericMasterDto> findAllQuotaClassifications();

    List<GenericMasterDto> findAllPeriodicityInterests();

    List<GenericMasterDto> findAllCreditTypes();

    List<GenericMasterDto> findAllCapitalAmortizations();

    List<GenericMasterDto> findAllFileBusinessTypes();

    List<GenericMasterDto> findAllAmariloConcepts();

    List<GenericMasterDto> findAllFiduciaryConcepts();

    List<GenericMasterDto> findAllDebtTypes();
}
