package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceThirdRepository extends JpaRepository<FinanceThirdEntity, Integer>, JpaSpecificationExecutor<FinanceThirdEntity> {
    Optional<FinanceThirdEntity> findByName(String name);
}
