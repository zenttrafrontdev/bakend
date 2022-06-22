package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {
}
