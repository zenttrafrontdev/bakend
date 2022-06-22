package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.financethird.FinanceThirdTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceThirdTypeRepository extends JpaRepository<FinanceThirdTypeEntity, Integer> {
}
