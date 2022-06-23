package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>, JpaSpecificationExecutor<ProjectEntity> {

    Optional<ProjectEntity> findByProjectCode(String projectCode);
}
