package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.DisbursementOperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisbursementOperationRepository extends JpaRepository<DisbursementOperationTypeEntity, Integer> {
    Optional<DisbursementOperationTypeEntity> findByName(String name);
}
