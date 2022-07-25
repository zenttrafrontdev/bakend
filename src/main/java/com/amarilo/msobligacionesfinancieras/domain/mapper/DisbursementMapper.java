package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisbursementMapper {
    DisbursementMapper INSTANCE = Mappers.getMapper(DisbursementMapper.class);

    List<DisbursementDto> disbursementEntityListToDisbursementDtoList(List<DisbursementEntity> disbursementEntityList);

    DisbursementEntity disbursementDtoToDisbursementEntity(DisbursementDto disbursementDto);

    DisbursementDto disbursementEntityToDisbursementDto(DisbursementEntity disbursementEntity);
}
