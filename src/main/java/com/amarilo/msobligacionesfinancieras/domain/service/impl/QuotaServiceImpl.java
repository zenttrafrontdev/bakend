package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.QuotaSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.QuotaMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.QuotaService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasApprovedQuotaDate;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasBankId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasBusinessAreaId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasExpirationQuotaDate;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasProjectId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasQuotaClassificationId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasQuotaTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository quotaRepository;

    public QuotaServiceImpl(QuotaRepository quotaRepository) {
        this.quotaRepository = quotaRepository;
    }

    @Override
    public PageResponseDto<QuotaDto> findAllQuotasBySearchCriteria(PageRequestDto<QuotaSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<QuotaEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = quotaRepository.findAll(pageable);
        } else {
            page = quotaRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = QuotaMapper.INSTANCE.quotaEntityListToQuotaDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public QuotaDto findById(Integer id) {
        return QuotaMapper.INSTANCE.quotaEntityToQuotaDto(quotaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El cupo no existe")));
    }

    @Override
    public QuotaDto saveQuota(QuotaDto quotaDto, List<MultipartFile> files) {
        validateQuotaDates(quotaDto);
        var quotaEntity = QuotaMapper.INSTANCE.quotaDtoToQuotaEntity(quotaDto);
        return QuotaMapper.INSTANCE.quotaEntityToQuotaDto(quotaRepository.save(quotaEntity));
    }

    private void validateQuotaDates(QuotaDto quotaDto){
        if(quotaDto.getApprovedQuotaDate().isAfter(quotaDto.getExpirationQuotaDate())){
            throw new BusinessException("La fecha de aprobación del cupo no puede ser mayor a la de vencimiento");
        }
    }

    private Specification<QuotaEntity> getSpecificationFromQuery(QuotaSearchCriteria searchCriteria) {
        Specification<QuotaEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getQuotaTypeId()).isPresent()) {
            specification = buildAndSpecification(null, hasQuotaTypeId(searchCriteria.getQuotaTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getQuotaClassificationId()).isPresent()) {
            specification = buildAndSpecification(specification, hasQuotaClassificationId(searchCriteria.getQuotaClassificationId()));
        }

        if (Optional.ofNullable(searchCriteria.getBusinessAreaId()).isPresent()) {
            specification = buildAndSpecification(specification, hasBusinessAreaId(searchCriteria.getBusinessAreaId()));
        }

        if (Optional.ofNullable(searchCriteria.getProjectId()).isPresent()) {
            specification = buildAndSpecification(specification, hasProjectId(searchCriteria.getProjectId()));
        }

        if (Optional.ofNullable(searchCriteria.getBankId()).isPresent()) {
            specification = buildAndSpecification(specification, hasBankId(searchCriteria.getBankId()));
        }

        if (Optional.ofNullable(searchCriteria.getApprovedQuotaDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasApprovedQuotaDate(searchCriteria.getApprovedQuotaDate()));
        }

        if (Optional.ofNullable(searchCriteria.getExpirationQuotaDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasExpirationQuotaDate(searchCriteria.getExpirationQuotaDate()));
        }

        return specification;
    }
}
