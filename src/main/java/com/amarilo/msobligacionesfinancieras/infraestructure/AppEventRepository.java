package com.amarilo.msobligacionesfinancieras.infraestructure;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.AppEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppEventRepository extends JpaRepository<AppEventEntity, Integer> {

    List<AppEventEntity> findAllByEventTypeAndProcessAndRecordId(String appEventType, String process, String recordId);
}
