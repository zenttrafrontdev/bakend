package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.infraestructure.entity.AppEventEntity;

public interface AppEventService {
    Integer saveAppEvent(AppEventEntity appEvent);

    String getLogs(String recordId, String appEventType, String process);
}
