package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
import com.amarilo.msobligacionesfinancieras.domain.enums.PeriodicityEnum;
import com.amarilo.msobligacionesfinancieras.domain.enums.ValueTypeEnum;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FeeMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FeeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeSpecification.hasName;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeSpecification.hasPeriodicity;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeSpecification.hasValueType;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeRepository feeRepository;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public FeeDto findById(Integer id) {
        return FeeMapper.INSTANCE.feeEntityToFeeDto(feeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("La tasa no existe")));
    }

    @Override
    public PageResponseDto<FeeDto> findAllFeeBySearchCriteria(PageRequestDto<FeeSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<FeeEntity> page;
        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = feeRepository.findAll(pageable);
        } else {
            page = feeRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = FeeMapper.INSTANCE.feeEntityListToFeeDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public void saveFee(FeeDto feeDto) {
        var feeEntity = FeeMapper.INSTANCE.feeDtoToFeeEntity(feeDto);
        validEnums(feeDto);
        feeRepository.save(feeEntity);
    }

    @Override
    public void updateFee(Integer id, FeeDto feeDto) {
        var feeEntity = FeeMapper.INSTANCE.feeDtoToFeeEntity(feeDto);
        feeEntity.setId(id);
        feeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("La tasa que desea actualizar no existe"));
        validEnums(feeDto);
        feeRepository.save(feeEntity);
    }

    private void validEnums(FeeDto feeDto) {
        PeriodicityEnum.getById(feeDto.getPeriodicity());
        ValueTypeEnum.getById(feeDto.getValueType());
    }

    private Specification<FeeEntity> getSpecificationFromQuery(FeeSearchCriteria searchCriteria) {
        Specification<FeeEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getName()).isPresent()) {
            specification = buildAndSpecification(null, hasName(searchCriteria.getName()));
        }

        if (Optional.ofNullable(searchCriteria.getPeriodicity()).isPresent()) {
            specification = buildAndSpecification(null, hasPeriodicity(searchCriteria.getPeriodicity()));
        }

        if (Optional.ofNullable(searchCriteria.getValueType()).isPresent()) {
            specification = buildAndSpecification(null, hasValueType(searchCriteria.getValueType()));
        }

        return specification;
    }
}
