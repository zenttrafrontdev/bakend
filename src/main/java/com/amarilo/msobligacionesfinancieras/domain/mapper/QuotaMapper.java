package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuotaMapper {
    QuotaMapper INSTANCE = Mappers.getMapper(QuotaMapper.class);

    List<QuotaDto> quotaEntityListToQuotaDtoList(List<QuotaEntity> quotaEntityList);

    QuotaEntity quotaDtoToQuotaEntity(QuotaDto quotaDto);

    QuotaDto quotaEntityToQuotaDto(QuotaEntity quotaEntity);
}
