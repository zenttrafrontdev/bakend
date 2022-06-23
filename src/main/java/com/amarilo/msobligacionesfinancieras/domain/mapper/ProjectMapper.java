package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    List<ProjectDto> projectListToProjectDtoList(List<ProjectEntity> projectList);

    ProjectEntity projectDtoToProject(ProjectDto projectDto);

    ProjectDto projectEntityToProjectDto(ProjectEntity projectEntity);
}
