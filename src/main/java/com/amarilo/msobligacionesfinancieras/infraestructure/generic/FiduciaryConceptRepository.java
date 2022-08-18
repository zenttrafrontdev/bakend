package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.FiduciaryConceptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FiduciaryConceptRepository extends JpaRepository<FiduciaryConceptEntity, Integer> {
    Optional<FiduciaryConceptEntity> findByName(String name);
}
