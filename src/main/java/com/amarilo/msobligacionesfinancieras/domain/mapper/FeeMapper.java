package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeMapper {
    FeeMapper INSTANCE = Mappers.getMapper(FeeMapper.class);

    List<FeeDto> feeEntityListToFeeDtoList(List<FeeEntity> feeEntityList);

    FeeEntity feeDtoToFeeEntity(FeeDto feeDto);

    FeeDto feeEntityToFeeDto(FeeEntity feeEntity);
}
