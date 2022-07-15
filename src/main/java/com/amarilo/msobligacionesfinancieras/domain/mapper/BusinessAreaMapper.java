package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.BusinessAreaDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BusinessAreaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessAreaMapper {
    BusinessAreaMapper INSTANCE = Mappers.getMapper(BusinessAreaMapper.class);

    List<BusinessAreaDto> businessAreaEntityListToBusinessAreaDtoList(List<BusinessAreaEntity> businessAreaEntityList);

    BusinessAreaEntity businessAreaDtoToBusinessAreaEntity(BusinessAreaDto businessAreaDto);

    BusinessAreaDto businessAreaEntityToBusinessAreaDto(BusinessAreaEntity businessAreaEntity);
}
