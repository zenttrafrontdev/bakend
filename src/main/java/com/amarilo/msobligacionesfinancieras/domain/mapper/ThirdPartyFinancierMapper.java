package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.ThirdPartyFinancierDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ThirdPartyFinancierEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ThirdPartyFinancierMapper {
    ThirdPartyFinancierMapper INSTANCE = Mappers.getMapper(ThirdPartyFinancierMapper.class);

    List<ThirdPartyFinancierDto> thirdPartyFinancierEntityListToThirdPartyFinancierDtoList(List<ThirdPartyFinancierEntity> thirdPartyFinancierEntityList);

    ThirdPartyFinancierEntity thirdPartyFinancierDtoToThirdPartyFinancierEntity(ThirdPartyFinancierDto thirdPartyFinancierDto);

    ThirdPartyFinancierDto thirdPartyFinancierEntityToThirdPartyFinancierDto(ThirdPartyFinancierEntity thirdPartyFinancierEntity);
}
