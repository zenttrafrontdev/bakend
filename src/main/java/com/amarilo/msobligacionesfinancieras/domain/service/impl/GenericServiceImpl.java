package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.GenericMasterMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.GenericService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalClassificationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiscalOrganizationTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AccountTypeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.BankRepository;
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

    public GenericServiceImpl(FinanceThirdTypeRepository financeThirdTypeRepository,
                              FiscalOrganizationTypeRepository fiscalOrganizationTypeRepository,
                              BankRepository bankRepository,
                              AccountTypeRepository accountTypeRepository,
                              WithholdingTaxGroupRepository withholdingTaxGroupRepository,
                              FiscalClassificationRepository fiscalClassificationRepository,
                              FiscalClassificationTypeRepository fiscalClassificationTypeRepository,
                              TaxClassificationRepository taxClassificationRepository) {
        this.financeThirdTypeRepository = financeThirdTypeRepository;
        this.fiscalOrganizationTypeRepository = fiscalOrganizationTypeRepository;
        this.bankRepository = bankRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.withholdingTaxGroupRepository = withholdingTaxGroupRepository;
        this.fiscalClassificationRepository = fiscalClassificationRepository;
        this.fiscalClassificationTypeRepository = fiscalClassificationTypeRepository;
        this.taxClassificationRepository = taxClassificationRepository;
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
}
