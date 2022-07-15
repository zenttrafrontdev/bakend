package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.BusinessAreaDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.BusinessAreaMapper;
import com.amarilo.msobligacionesfinancieras.domain.mapper.GenericMasterMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.GenericService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AccountTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BankRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BusinessAreaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.CapitalAmortizationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.CreditTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FileBusinessTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalOrganizationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PeriodicityInterestRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.QuotaClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.QuotaTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.TaxClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.WithholdingTaxGroupRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.GenericMasterEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenericServiceImpl implements GenericService {

    private final FinanceThirdTypeRepository financeThirdTypeRepository;
    private final FiscalOrganizationTypeRepository fiscalOrganizationTypeRepository;
    private final BankRepository bankRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final WithholdingTaxGroupRepository withholdingTaxGroupRepository;
    private final FiscalClassificationRepository fiscalClassificationRepository;
    private final FiscalClassificationTypeRepository fiscalClassificationTypeRepository;
    private final TaxClassificationRepository taxClassificationRepository;

    private final QuotaTypeRepository quotaTypeRepository;
    private final QuotaClassificationRepository quotaClassificationRepository;
    private final PeriodicityInterestRepository periodicityInterestRepository;
    private final CreditTypeRepository creditTypeRepository;
    private final CapitalAmortizationRepository capitalAmortizationRepository;
    private final BusinessAreaRepository businessAreaRepository;
    private final FileBusinessTypeRepository fileBusinessTypeRepository;

    public GenericServiceImpl(FinanceThirdTypeRepository financeThirdTypeRepository,
                              FiscalOrganizationTypeRepository fiscalOrganizationTypeRepository,
                              BankRepository bankRepository,
                              AccountTypeRepository accountTypeRepository,
                              WithholdingTaxGroupRepository withholdingTaxGroupRepository,
                              FiscalClassificationRepository fiscalClassificationRepository,
                              FiscalClassificationTypeRepository fiscalClassificationTypeRepository,
                              TaxClassificationRepository taxClassificationRepository,
                              QuotaTypeRepository quotaTypeRepository,
                              QuotaClassificationRepository quotaClassificationRepository,
                              PeriodicityInterestRepository periodicityInterestRepository,
                              CreditTypeRepository creditTypeRepository,
                              CapitalAmortizationRepository capitalAmortizationRepository,
                              BusinessAreaRepository businessAreaRepository,
                              FileBusinessTypeRepository fileBusinessTypeRepository) {
        this.financeThirdTypeRepository = financeThirdTypeRepository;
        this.fiscalOrganizationTypeRepository = fiscalOrganizationTypeRepository;
        this.bankRepository = bankRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.withholdingTaxGroupRepository = withholdingTaxGroupRepository;
        this.fiscalClassificationRepository = fiscalClassificationRepository;
        this.fiscalClassificationTypeRepository = fiscalClassificationTypeRepository;
        this.taxClassificationRepository = taxClassificationRepository;
        this.quotaTypeRepository = quotaTypeRepository;
        this.quotaClassificationRepository = quotaClassificationRepository;
        this.periodicityInterestRepository = periodicityInterestRepository;
        this.creditTypeRepository = creditTypeRepository;
        this.capitalAmortizationRepository = capitalAmortizationRepository;
        this.businessAreaRepository = businessAreaRepository;
        this.fileBusinessTypeRepository = fileBusinessTypeRepository;
    }

    @Override
    public List<GenericMasterDto> findAllFinanceThirdTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                financeThirdTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllFiscalOrganizationTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                fiscalOrganizationTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllBanks() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                bankRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllAccountTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                accountTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllWithholdingTaxGroups() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                withholdingTaxGroupRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllFiscalClassifications() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                fiscalClassificationRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllFiscalClassificationTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                fiscalClassificationTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllTaxClassifications() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                taxClassificationRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<BusinessAreaDto> findAllBusinessAreas() {
        return BusinessAreaMapper.INSTANCE.businessAreaEntityListToBusinessAreaDtoList(businessAreaRepository.findAll());
    }

    @Override
    public List<GenericMasterDto> findAllQuotaTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                quotaTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllQuotaClassifications() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                quotaClassificationRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllPeriodicityInterests() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                periodicityInterestRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllCreditTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                creditTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllCapitalAmortizations() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                capitalAmortizationRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllFileBusinessTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                fileBusinessTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }
}
