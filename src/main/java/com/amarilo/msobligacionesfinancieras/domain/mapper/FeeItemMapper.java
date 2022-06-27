package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeeItemMapper {
    FeeItemMapper INSTANCE = Mappers.getMapper(FeeItemMapper.class);

    List<FeeItemDto> feeItemEntityListToFeeItemDtoList(List<FeeItemEntity> feeItemEntityList);

    FeeItemEntity feeItemDtoToFeeItemEntity(FeeItemDto feeItemDto);

    FeeItemDto feeItemEntityToFeeItemDto(FeeItemEntity feeItemEntity);
}
