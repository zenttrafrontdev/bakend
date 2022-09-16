package com.amarilo.msobligacionesfinancieras.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppEventTypeEnum {
    MIGRATIONS("Migraciones");

    private final String value;
}
