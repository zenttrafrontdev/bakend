package com.amarilo.msobligacionesfinancieras.fixture;

import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;

public class ProjectFixture {
    public static ProjectEntity getProjectEntity(){
        return ProjectEntity.builder()
                .id(1)
                .projectCode("001")
                .projectName("Project")
                .groupCode("001")
                .groupName("group")
                .consolidatorCode("001")
                .consolidatorName("consolidator")
                .buildersCreditBank("BBVA")
                .paymentType("CREDIT")
                .status("ACTIVO")
                .build();
    }

    public static ProjectDto getProjectDto(){
        return ProjectDto.builder()
                .id(1)
                .projectCode("001")
                .projectName("Project")
                .groupCode("001")
                .groupName("group")
                .consolidatorCode("001")
                .consolidatorName("consolidator")
                .buildersCreditBank("BBVA")
                .paymentType("CREDIT")
                .status("ACTIVO")
                .build();
    }
    public static ProjectDto getProjectDtoWithId(Integer id){
        return ProjectDto.builder()
                .id(id)
                .projectCode("001")
                .projectName("Project")
                .groupCode("001")
                .groupName("group")
                .consolidatorCode("001")
                .consolidatorName("consolidator")
                .buildersCreditBank("BBVA")
                .paymentType("CREDIT")
                .status("ACTIVO")
                .build();
    }
}
