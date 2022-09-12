package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentDto {
    private Integer id;
    @NotNull(message = "La fecha del pago es requerido")
    private LocalDate date;
    @NotBlank(message = "El capital del pago es requerido")
    private String capital;
    @NotBlank(message = "El valor del pago es requerido")
    private String value;
    private LocalDate appliedDate;
    private String oracleId;
    @NotBlank(message = "Los intereses del pago es requerido")
    private String rates;
    private String totalValue;
    @NotNull(message = "El desembolso es requerido")
    private DisbursementGroupDto disbursementGroup;
    @NotNull(message = "La fuente de pago es requerido")
    private GenericMasterDto paymentSource;
    @NotNull(message = "El tipo de pago es requerido")
    private GenericMasterDto paymentType;
    private List<PaymentDetailOtherConceptDto> paymentDetailOtherConcepts;
}
