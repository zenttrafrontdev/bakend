package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.controller.response.IProjectConsolidator;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>, JpaSpecificationExecutor<ProjectEntity> {

    Optional<ProjectEntity> findByProjectCode(String projectCode);

    @Query(value = "select distinct \n" +
            "codigo_consolidador as consolidatorCode,\n" +
            "nombre_consolidador as consolidatorName\n" +
            "from proyectos p \n" +
            "group by codigo_consolidador \n" +
            "order by nombre_consolidador",
            nativeQuery = true)
    List<IProjectConsolidator> findAllDistinctConsolidatorName();

    List<ProjectEntity> findAllByConsolidatorCode(String consolidatorName);
}
