package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.domain.financethird.FinanceThirdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceThirdRepository extends JpaRepository<FinanceThirdEntity, Integer>, JpaSpecificationExecutor<FinanceThirdEntity> {
}
