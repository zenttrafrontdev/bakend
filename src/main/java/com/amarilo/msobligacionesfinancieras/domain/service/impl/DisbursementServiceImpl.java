package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.DisbursementMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.DisbursementRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DisbursementServiceImpl implements DisbursementService {

    private final DisbursementRepository disbursementRepository;

    public DisbursementServiceImpl(DisbursementRepository disbursementRepository) {
        this.disbursementRepository = disbursementRepository;
    }

    @Override
    public PageResponseDto<DisbursementDto> findAllDisbursementsBySearchCriteria(PageRequestDto<DisbursementSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<DisbursementEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = disbursementRepository.findAll(pageable);
        } else {
            page = disbursementRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }
        var content = DisbursementMapper.INSTANCE.disbursementEntityListToDisbursementDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public DisbursementDto findById(Integer id) {
        return DisbursementMapper.INSTANCE.disbursementEntityToDisbursementDto(disbursementRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El desembolso no existe")));
    }

    @Override
    public DisbursementDto saveDisbursement(DisbursementDto disbursementDto) {
        DisbursementEntity disbursementEntity = DisbursementMapper.INSTANCE.disbursementDtoToDisbursementEntity(disbursementDto);
        return DisbursementMapper.INSTANCE.disbursementEntityToDisbursementDto(disbursementRepository.save(disbursementEntity));
    }

    private Specification<DisbursementEntity> getSpecificationFromQuery(DisbursementSearchCriteria searchCriteria) {
        Specification<DisbursementEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getProjectId()).isPresent()) {
            specification = null;//buildAndSpecification(null, hasQuotaTypeId(searchCriteria.getQuotaTypeId()));
        }
        return specification;
    }
}
