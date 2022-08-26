package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementGroupDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DisbursementGroupMapper {
    DisbursementGroupMapper INSTANCE = Mappers.getMapper(DisbursementGroupMapper.class);

    List<DisbursementGroupDto> disbursementGroupEntityListToDisbursementGroupDtoList(List<DisbursementGroupEntity> disbursementGroupEntityList);

    DisbursementGroupEntity disbursementGroupDtoToDisbursementGroupEntity(DisbursementGroupDto disbursementGroupDto);

    DisbursementGroupDto disbursementGroupEntityToDisbursementGroupDto(DisbursementGroupEntity disbursementGroupEntity);
}
