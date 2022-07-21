package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDetailDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuotaDetailMapper {
    QuotaDetailMapper INSTANCE = Mappers.getMapper(QuotaDetailMapper.class);

    List<QuotaDetailDto> quotaDetailEntityListToQuotaDetailDtoList(List<QuotaDetailEntity> quotaDetailEntityList);

    QuotaDetailEntity quotaDetailDtoToQuotaDetailEntity(QuotaDetailDto quotaDetailDto);

    QuotaDetailDto quotaDetailEntityToQuotaDetailDto(QuotaDetailEntity quotaDetailEntity);
}
