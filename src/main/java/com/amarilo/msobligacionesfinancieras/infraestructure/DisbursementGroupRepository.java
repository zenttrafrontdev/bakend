package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisbursementGroupRepository extends JpaRepository<DisbursementGroupEntity, Integer>, JpaSpecificationExecutor<DisbursementGroupEntity> {
    Optional<DisbursementGroupEntity> findByObligationNumber(String obligationNumber);
}
