package com.amarilo.msobligacionesfinancieras.domain.enums;

import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValueTypeEnum {
    NUMERIC(1),
    ALPHANUMERIC(2),
    PERCENTAGE(3);

    private final Integer value;

    public static ValueTypeEnum getById(Integer id) {
        for(ValueTypeEnum e : values()) {
            if(e.value.equals(id)) return e;
        }
        throw new BusinessException("Tipo de Valor no válido");
    }
}
