package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FileBusinessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileBusinessRepository extends JpaRepository<FileBusinessEntity, Integer> {
}
