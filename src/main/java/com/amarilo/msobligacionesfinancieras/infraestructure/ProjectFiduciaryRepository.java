package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectFiduciaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectFiduciaryRepository extends JpaRepository<ProjectFiduciaryEntity, Integer> {

    Optional<ProjectFiduciaryEntity> findByProjectProjectCode(String projectCode);
}
