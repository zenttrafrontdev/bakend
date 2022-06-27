package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.ProjectSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.ProjectMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.ProjectService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasBuildersCreditBank;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasConsolidatorCode;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasConsolidatorName;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasGroupCode;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasGroupName;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasPaymentType;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasProjectCode;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasProjectName;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.ProjectSpecification.hasStatus;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public PageResponseDto<ProjectDto> findAllProjectsBySearchCriteria(PageRequestDto<ProjectSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<ProjectEntity> page;
        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = projectRepository.findAll(pageable);
        } else {
            page = projectRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = ProjectMapper.INSTANCE.projectListToProjectDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public ProjectDto findById(Integer id) {
        return ProjectMapper.INSTANCE.projectEntityToProjectDto(projectRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El proyecto no existe")));
    }

    @Transactional
    @Override
    public void saveProjects(List<ProjectDto> projectList) {
        for (ProjectDto projectDto : projectList) {
            var projectEntity = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);
            Optional<ProjectEntity> projectEntityOptional = projectRepository.findByProjectCode(projectEntity.getProjectCode());
            if (projectEntityOptional.isPresent()) {
                projectEntity.setId(projectEntityOptional.get().getId());
                projectRepository.save(projectEntity);
            } else {
                projectRepository.save(projectEntity);
            }
        }
    }

    private Specification<ProjectEntity> getSpecificationFromQuery(ProjectSearchCriteria searchCriteria) {
        Specification<ProjectEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getProjectCode()).isPresent()) {
            specification = buildAndSpecification(null, hasProjectCode(searchCriteria.getProjectCode()));
        }

        if (Optional.ofNullable(searchCriteria.getProjectName()).isPresent()) {
            specification = buildAndSpecification(null, hasProjectName(searchCriteria.getProjectName()));
        }

        if (Optional.ofNullable(searchCriteria.getGroupCode()).isPresent()) {
            specification = buildAndSpecification(null, hasGroupCode(searchCriteria.getGroupCode()));
        }

        if (Optional.ofNullable(searchCriteria.getGroupName()).isPresent()) {
            specification = buildAndSpecification(null, hasGroupName(searchCriteria.getGroupName()));
        }

        if (Optional.ofNullable(searchCriteria.getConsolidatorCode()).isPresent()) {
            specification = buildAndSpecification(null, hasConsolidatorCode(searchCriteria.getConsolidatorCode()));
        }

        if (Optional.ofNullable(searchCriteria.getConsolidatorName()).isPresent()) {
            specification = buildAndSpecification(null, hasConsolidatorName(searchCriteria.getConsolidatorName()));
        }

        if (Optional.ofNullable(searchCriteria.getBuildersCreditBank()).isPresent()) {
            specification = buildAndSpecification(null, hasBuildersCreditBank(searchCriteria.getBuildersCreditBank()));
        }

        if (Optional.ofNullable(searchCriteria.getPaymentType()).isPresent()) {
            specification = buildAndSpecification(null, hasPaymentType(searchCriteria.getPaymentType()));
        }

        if (Optional.ofNullable(searchCriteria.getStatus()).isPresent()) {
            specification = buildAndSpecification(null, hasStatus(searchCriteria.getStatus()));
        }

        return specification;
    }
}
