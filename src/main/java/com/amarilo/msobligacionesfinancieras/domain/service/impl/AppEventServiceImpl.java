package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.service.AppEventService;
import com.amarilo.msobligacionesfinancieras.infraestructure.AppEventRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.AppEventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppEventServiceImpl implements AppEventService {

    private final AppEventRepository appEventRepository;

    @Override
    public Integer saveAppEvent(AppEventEntity appEvent) {
        var appEventEntitySaved = appEventRepository.save(appEvent);
        return appEventEntitySaved.getId();
    }

    @Override
    public String getLogs(String recordId, String appEventType, String process) {
        var appEventList = appEventRepository.findAllByEventTypeAndProcessAndRecordId(appEventType, process, recordId);
        return appEventList.stream()
                .map(x -> x.getEventDate().toString() + " - " + x.getLog())
                .collect(Collectors.joining());
    }
}
