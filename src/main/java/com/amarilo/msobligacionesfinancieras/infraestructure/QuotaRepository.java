package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuotaRepository extends JpaRepository<QuotaEntity, Integer>, JpaSpecificationExecutor<QuotaEntity> {
    @Query(value = "select c.*\n" +
            "from proyectos p \n" +
            "inner join detalle_cupos dc on dc.proyecto_id = p.id \n" +
            "inner join cupos c on c.id = dc.cupo_id \n" +
            "where p.codigo_proyecto =:projectCode \n" +
            "and c.cupo_disponible > 0",
            nativeQuery = true)
    Optional<QuotaEntity> findByQuotaDetailsProjectProjectCodeAndWithAvailableQuota(@Param("projectCode") String projectCode);

    @Query(value = "select c.* \n" +
            "from proyectos p \n" +
            "inner join detalle_cupos dc on dc.proyecto_id = p.id \n" +
            "inner join cupos c on c.id = dc.cupo_id \n" +
            "where p.codigo_consolidador =:consolidatorCode \n" +
            "and c.cupo_disponible > 0\n" +
            "group by c.id " +
            "order by p.nombre_proyecto asc",
            nativeQuery = true)
    List<QuotaEntity> findAllByQuotaDetailsProjectConsolidatorCodeAndAvailableQuotaGreaterThanZero(@Param("consolidatorCode") String consolidatorCode);
}
