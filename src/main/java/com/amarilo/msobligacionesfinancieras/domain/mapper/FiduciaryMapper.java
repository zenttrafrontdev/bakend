package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.FiduciaryDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FiduciaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FiduciaryMapper {
    FiduciaryMapper INSTANCE = Mappers.getMapper(FiduciaryMapper.class);

    List<FiduciaryDto> fiduciaryEntityListToFiduciaryDtoList(List<FiduciaryEntity> fiduciaryEntityList);

    FiduciaryEntity fiduciaryDtoToFiduciaryEntity(FiduciaryDto fiduciaryDto);

    FiduciaryDto fiduciaryEntityToFiduciaryDto(FiduciaryEntity fiduciaryEntity);
}
