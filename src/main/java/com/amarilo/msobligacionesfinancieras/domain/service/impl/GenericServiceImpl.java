package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.BusinessAreaDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.BusinessAreaMapper;
import com.amarilo.msobligacionesfinancieras.domain.mapper.GenericMasterMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.GenericService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AccountTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AccountingStatusRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AmariloConceptRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BankRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BusinessAreaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.CapitalAmortizationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.CreditTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.DebtTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.DisbursementOperationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiduciaryConceptRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FileBusinessTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalOrganizationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PaymentConceptRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PaymentSourceRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PaymentStatusRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PaymentTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.PeriodicityInterestRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.QuotaClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.QuotaTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.TaxClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.WithholdingTaxGroupRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.GenericMasterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
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
    private final AmariloConceptRepository amariloConceptRepository;
    private final FiduciaryConceptRepository fiduciaryConceptRepository;
    private final DebtTypeRepository debtTypeRepository;
    private final DisbursementOperationRepository disbursementOperationRepository;
    private final PaymentSourceRepository paymentSourceRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentConceptRepository paymentConceptRepository;
    private final PaymentStatusRepository paymentStatusRepository;
    private final AccountingStatusRepository accountingStatusRepository;
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

    @Override
    public List<GenericMasterDto> findAllAmariloConcepts() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                amariloConceptRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllFiduciaryConcepts() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                fiduciaryConceptRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllDebtTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                debtTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllDisbursementOperationTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                disbursementOperationRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllPaymentTypes() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                paymentTypeRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllPaymentSources() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                paymentSourceRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllPaymentConcepts() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                paymentConceptRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllPaymentStatus() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                paymentStatusRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public List<GenericMasterDto> findAllAccountingStatus() {
        return GenericMasterMapper.INSTANCE.genericMasterListToGenericMasterDtoList(
                accountingStatusRepository.findAll()
                        .stream()
                        .map(GenericMasterEntity.class::cast)
                        .collect(Collectors.toList()));
    }
}
