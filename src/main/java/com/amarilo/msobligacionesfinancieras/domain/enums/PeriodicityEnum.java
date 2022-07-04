package com.amarilo.msobligacionesfinancieras.domain.enums;

import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeriodicityEnum {
    DAILY(1),
    WEEKLY(2);

    private final Integer value;

    public static PeriodicityEnum getById(Integer id) {
        for(PeriodicityEnum e : values()) {
            if(e.value.equals(id)) return e;
        }
        throw new BusinessException("Tipo de Periodicidad no válido");
    }
}
