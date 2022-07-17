package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaFileBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotaFileBusinessRepository extends JpaRepository<QuotaFileBusinessEntity, Integer> {
    List<QuotaFileBusinessEntity> findAllByQuotaId(Integer id);
}
