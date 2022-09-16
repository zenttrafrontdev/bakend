package com.amarilo.msobligacionesfinancieras.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppEventProcessEnum {
    ACCOUNT_PAYABLE("Cuentas por Pagar");

    private final String value;
}
