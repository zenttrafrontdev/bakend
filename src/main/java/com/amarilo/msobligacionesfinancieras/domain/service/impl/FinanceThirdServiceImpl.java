package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FinanceThirdSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FinanceThirdMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FinanceThirdService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasAccountNumber;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasAccountTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasBankId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasContributorId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasFinanceThirdTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasFiscalClassificationId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasFiscalClassificationTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasFiscalOrganizationTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasIdentification;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasName;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasTaxClassificationId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasTaxWithholding;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FinanceThirdSpecification.hasWithholdingTaxGroupId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Slf4j
@Service
public class FinanceThirdServiceImpl implements FinanceThirdService {

    private final FinanceThirdRepository financeThirdRepository;

    public FinanceThirdServiceImpl(FinanceThirdRepository financeThirdRepository) {
        this.financeThirdRepository = financeThirdRepository;
    }

    @Override
    public List<FinanceThirdDto> findAllFinanceThird() {
        return FinanceThirdMapper.INSTANCE.financeThirdListToFinanceThirdDtoList(financeThirdRepository.findAll());
    }

    @Override
    public PageResponseDto<FinanceThirdDto> findAllFinanceThirdBySearchCriteria(PageRequestDto<FinanceThirdSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<FinanceThirdEntity> page;
        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = financeThirdRepository.findAll(pageable);
        } else {
            page = financeThirdRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = FinanceThirdMapper.INSTANCE.financeThirdListToFinanceThirdDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    private Specification<FinanceThirdEntity> getSpecificationFromQuery(FinanceThirdSearchCriteria searchCriteria) {
        Specification<FinanceThirdEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getName()).isPresent()) {
            specification = buildAndSpecification(null, hasName(searchCriteria.getName()));
        }

        if (Optional.ofNullable(searchCriteria.getIdentification()).isPresent()) {
            specification = buildAndSpecification(specification, hasIdentification(searchCriteria.getIdentification()));
        }

        if (Optional.ofNullable(searchCriteria.getContributorId()).isPresent()) {
            specification = buildAndSpecification(specification, hasContributorId(searchCriteria.getContributorId()));
        }

        if (Optional.ofNullable(searchCriteria.getAccountNumber()).isPresent()) {
            specification = buildAndSpecification(specification, hasAccountNumber(searchCriteria.getAccountNumber()));
        }

        if (Optional.ofNullable(searchCriteria.getTaxWithholding()).isPresent()) {
            specification = buildAndSpecification(specification, hasTaxWithholding(searchCriteria.getTaxWithholding()));
        }

        if (Optional.ofNullable(searchCriteria.getFinanceThirdTypeId()).isPresent() && searchCriteria.getFinanceThirdTypeId() > 0) {
            specification = buildAndSpecification(specification, hasFinanceThirdTypeId(searchCriteria.getFinanceThirdTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getFiscalOrganizationTypeId()).isPresent() && searchCriteria.getFiscalOrganizationTypeId() > 0) {
            specification = buildAndSpecification(specification, hasFiscalOrganizationTypeId(searchCriteria.getFiscalOrganizationTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getBankId()).isPresent() && searchCriteria.getBankId() > 0) {
            specification = buildAndSpecification(specification, hasBankId(searchCriteria.getBankId()));
        }

        if (Optional.ofNullable(searchCriteria.getAccountTypeId()).isPresent() && searchCriteria.getAccountTypeId() > 0) {
            specification = buildAndSpecification(specification, hasAccountTypeId(searchCriteria.getAccountTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getWithholdingTaxGroupId()).isPresent() && searchCriteria.getWithholdingTaxGroupId() > 0) {
            specification = buildAndSpecification(specification, hasWithholdingTaxGroupId(searchCriteria.getWithholdingTaxGroupId()));
        }

        if (Optional.ofNullable(searchCriteria.getFiscalClassificationId()).isPresent() && searchCriteria.getFiscalClassificationId() > 0) {
            specification = buildAndSpecification(specification, hasFiscalClassificationId(searchCriteria.getFiscalClassificationId()));
        }

        if (Optional.ofNullable(searchCriteria.getFiscalClassificationTypeId()).isPresent() && searchCriteria.getFiscalClassificationTypeId() > 0) {
            specification = buildAndSpecification(specification, hasFiscalClassificationTypeId(searchCriteria.getFiscalClassificationTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getTaxClassificationId()).isPresent() && searchCriteria.getTaxClassificationId() > 0) {
            specification = buildAndSpecification(specification, hasTaxClassificationId(searchCriteria.getTaxClassificationId()));
        }

        return specification;
    }
}
