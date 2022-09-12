package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentConceptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentConceptRepository extends JpaRepository<PaymentConceptEntity, Integer> {
}
