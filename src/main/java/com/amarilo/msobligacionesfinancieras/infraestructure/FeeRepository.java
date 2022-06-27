package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<FeeEntity, Integer>, JpaSpecificationExecutor<FeeEntity> {
}
