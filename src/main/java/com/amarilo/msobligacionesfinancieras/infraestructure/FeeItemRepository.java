package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FeeItemRepository extends JpaRepository<FeeItemEntity, Integer>, JpaSpecificationExecutor<FeeItemEntity> {

    @Query(value = "select \n" +
            "case when count(1)=0 then 'true' else 'false' end \n" +
            "from periodos_tasas pt \n" +
            "where pt.tasa_id =:feeId\n" +
            "AND DATE(:startDate) between pt.fecha_inicio and pt.fecha_final  \n" +
            "OR DATE(:endDate) between pt.fecha_inicio and pt.fecha_final", nativeQuery = true)
    boolean validateIfDateRangeIsNotOverlapping(@Param("feeId") Integer feeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "select \n" +
            "case when count(1)=0 then 'true' else 'false' end \n" +
            "from periodos_tasas pt \n" +
            "where pt.tasa_id =:feeId\n" +
            "and DATE(:endDate) <= pt.fecha_final", nativeQuery = true)
    boolean validateIfPeriodDateIsOk(@Param("feeId") Integer feeId, @Param("endDate") LocalDate endDate);
}
