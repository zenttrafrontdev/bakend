package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.commons.Utility;
import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;
import com.amarilo.msobligacionesfinancieras.domain.enums.ValueTypeEnum;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FeeItemMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeItemService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FeeItemRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasEndDateLessThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasFeeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasStartDateGreaterThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasValue;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Service
public class FeeItemServiceImpl implements FeeItemService {

    private final FeeItemRepository feeItemRepository;

    public FeeItemServiceImpl(FeeItemRepository feeItemRepository) {
        this.feeItemRepository = feeItemRepository;
    }

    private static final String FIELD_VALUE = "valor";

    @Override
    public FeeItemDto findById(Integer id) {
        return FeeItemMapper.INSTANCE.feeItemEntityToFeeItemDto(feeItemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El periodo de la tasa no existe")));
    }

    @Override
    public PageResponseDto<FeeItemDto> findAllFeeItemBySearchCriteria(PageRequestDto<FeeItemSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<FeeItemEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = feeItemRepository.findAll(pageable);
        } else {
            page = feeItemRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = FeeItemMapper.INSTANCE.feeItemEntityListToFeeItemDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public void saveFeeItem(FeeItemDto feeItemDto) {
        validateValueType(feeItemDto);
        validateDates(feeItemDto);

        var feeItemEntity = FeeItemMapper.INSTANCE.feeItemDtoToFeeItemEntity(feeItemDto);
        feeItemRepository.save(feeItemEntity);
    }

    @Override
    public void updateFeeItem(FeeItemDto feeItemDto) {
        validateValueType(feeItemDto);
        validateDates(feeItemDto);
        feeItemRepository.findById(feeItemDto.getId())
                .orElseThrow(() -> new BusinessException("El periodo de la tasa que desea actualizar no existe"));
        var feeItemEntity = FeeItemMapper.INSTANCE.feeItemDtoToFeeItemEntity(feeItemDto);
        feeItemRepository.save(feeItemEntity);
    }

    private void validateValueType(FeeItemDto feeItemDto) {
        var valueType = ValueTypeEnum.getById(feeItemDto.getFee().getValueType());
        switch (valueType) {
            case NUMERIC:
                Utility.validateNumericField(FIELD_VALUE, feeItemDto.getValue());
                break;
            case PERCENTAGE:
                Long longValue = Utility.validateNumericField(FIELD_VALUE, feeItemDto.getValue());
                Utility.validatePercentageField(FIELD_VALUE, longValue);
                break;
            default:
                break;
        }
    }

    private void validateDates(FeeItemDto feeItemDto) {
        if (feeItemDto.getStartDate().isAfter(feeItemDto.getEndDate())) {
            throw new BusinessException("El periodo de fecha no es válido");
        }

        if (!feeItemRepository.validateIfDateRangeIsNotOverlapping(feeItemDto.getFee().getId(), feeItemDto.getStartDate(), feeItemDto.getEndDate())) {
            throw new BusinessException("Ya se tiene una tasa para el periodo seleccionado");
        }

        if (!feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(feeItemDto.getFee().getId(), feeItemDto.getEndDate())) {
            throw new BusinessException("No se permite guardar registros de fechas pasadas a la ultima vigente.");
        }

        //TODO verificar con negocio está validación
        /*
        if (!"UVR".equals(feeItemDto.getFee().getName())
                && (feeItemDto.getStartDate().isAfter(LocalDate.now())
                || feeItemDto.getEndDate().isAfter(LocalDate.now())
        )) {
            throw new BusinessException("No se debe permitir adicionar tasas de vigencias futuras al dia del ingreso con excepcion de la tasa UVR");
        } else if (feeItemDto.getEndDate().isAfter(LocalDate.now().plus(14, ChronoUnit.DAYS))) {
            throw new BusinessException("Solo se permite ingresar un periodo de fecha hasta 15 días futuros");
        }
         */
    }

    private Specification<FeeItemEntity> getSpecificationFromQuery(FeeItemSearchCriteria searchCriteria) {
        Specification<FeeItemEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getFeeId()).isPresent()) {
            specification = buildAndSpecification(null, hasFeeId(searchCriteria.getFeeId()));
        }

        if (Optional.ofNullable(searchCriteria.getValue()).isPresent()) {
            specification = buildAndSpecification(specification, hasValue(searchCriteria.getValue()));
        }

        if (Optional.ofNullable(searchCriteria.getStartDate()).isPresent() && Optional.ofNullable(searchCriteria.getEndDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasStartDateGreaterThan(searchCriteria.getStartDate()));
            specification = buildAndSpecification(specification, hasEndDateLessThan(searchCriteria.getEndDate()));
        }

        return specification;
    }
}
