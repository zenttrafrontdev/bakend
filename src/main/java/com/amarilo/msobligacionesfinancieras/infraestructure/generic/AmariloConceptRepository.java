package com.amarilo.msobligacionesfinancieras.infraestructure.generic;

import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AmariloConceptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmariloConceptRepository extends JpaRepository<AmariloConceptEntity, Integer> {
    Optional<AmariloConceptEntity> findByName(String name);
}
