package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDetailOtherConceptDto {
    private Integer id;
    @NotNull(message = "El concepto de pago no puede ser vacío")
    private GenericMasterDto paymentConcept;
    @NotBlank(message = "El valor del concepto no puede ser vacío")
    private String value;
}
