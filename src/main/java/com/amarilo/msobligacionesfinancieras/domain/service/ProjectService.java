package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.ProjectSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.IProjectConsolidator;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    PageResponseDto<ProjectDto> findAllProjectsBySearchCriteria(PageRequestDto<ProjectSearchCriteria> pageRequestDto);

    ProjectDto findById(Integer id);

    void saveProjects(List<ProjectDto> projectList);

    List<IProjectConsolidator> findAllDistinctByConsolidatorName();

    List<ProjectDto> findAllByConsolidatorCode(String consolidatorName);
}
