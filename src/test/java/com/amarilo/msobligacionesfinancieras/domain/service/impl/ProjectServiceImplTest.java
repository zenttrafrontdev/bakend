package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.ProjectSearchCriteria;
import com.amarilo.msobligacionesfinancieras.domain.service.ProjectService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static com.amarilo.msobligacionesfinancieras.fixture.ProjectFixture.getProjectDto;
import static com.amarilo.msobligacionesfinancieras.fixture.ProjectFixture.getProjectDtoWithId;
import static com.amarilo.msobligacionesfinancieras.fixture.ProjectFixture.getProjectEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ProjectServiceImplTest {

    @Autowired
    ProjectService projectService;

    @MockBean
    ProjectRepository projectRepository;

    @Test
    void findAllProjectsBySearchCriteria_WithoutQueryData() {
        //given
        PageRequestDto<ProjectSearchCriteria> request = getPageRequestDto(null);
        var item = getProjectEntity();
        var list = List.of(item);

        //when
        when(projectRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));
        var result = projectService.findAllProjectsBySearchCriteria(request);

        //then
        Assertions.assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findAllProjectsBySearchCriteria_WithNoResults() {
        //given
        PageRequestDto<ProjectSearchCriteria> request = getPageRequestDto(ProjectSearchCriteria.builder()
                .projectCode("001")
                .projectName("Project")
                .groupCode("001")
                .groupName("group")
                .consolidatorCode("001")
                .consolidatorName("consolidator")
                .buildersCreditBank("BBVA")
                .paymentType("CREDIT")
                .status("ACTIVO")
                .build());

        //when
        when(projectRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = projectService.findAllProjectsBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }

    @Test
    void findById_WithResults() {
        //given
        Optional<ProjectEntity> projectEntityOptional = Optional.of(getProjectEntity());
        //when
        when(projectRepository.findById(any())).thenReturn(projectEntityOptional);
        var result = projectService.findById(1);
        //then
        Assertions.assertEquals(projectEntityOptional.get().getId(), result.getId());
    }

    @Test
    void findById_WithNoResults() throws BusinessException {
        //when
        when(projectRepository.findById(any())).thenReturn(Optional.empty());
        //then
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> projectService.findById(1));

        Assertions.assertEquals("El proyecto no existe", thrown.getMessage());
    }

    @Test
    void saveProjects() {
        //given
        var projectOne = getProjectDto();
        var projectTwo = getProjectDtoWithId(2);
        var list = List.of(projectOne, projectTwo);

        //when
        when(projectRepository.findByProjectCode(anyString())).thenReturn(Optional.empty()).thenReturn(Optional.of(getProjectEntity()));
        when(projectRepository.save(any())).thenReturn(null);
        projectService.saveProjects(list);

        //then
        verify(projectRepository, times(2)).save(any());
    }
}