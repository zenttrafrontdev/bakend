package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.FileBusinessDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FileBusinessEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FileBusinessMapper {
    FileBusinessMapper INSTANCE = Mappers.getMapper(FileBusinessMapper.class);

    List<FileBusinessDto> fileBusinessEntityListToFileBusinessDtoList(List<FileBusinessEntity> fileBusinessEntityList);

    FileBusinessEntity fileBusinessDtoToFileBusinessEntity(FileBusinessDto businessDto);

    FileBusinessDto fileBusinessEntityToFileBusinessDto(FileBusinessEntity fileBusinessEntity);
}
