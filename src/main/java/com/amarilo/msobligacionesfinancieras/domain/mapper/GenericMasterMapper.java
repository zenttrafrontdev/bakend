package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.GenericMasterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenericMasterMapper {
    GenericMasterMapper INSTANCE = Mappers.getMapper(GenericMasterMapper.class);

    List<GenericMasterDto> genericMasterListToGenericMasterDtoList(List<GenericMasterEntity> genericMasterList);

    GenericMasterEntity genericMasterDtoToGenericMaster(GenericMasterDto genericMasterDto);

    GenericMasterDto genericMasterEntityToGenericMasterDto(GenericMasterEntity genericMasterEntity);
}
