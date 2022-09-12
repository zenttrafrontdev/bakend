package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSourceRepository extends JpaRepository<PaymentSourceEntity, Integer> {
}
