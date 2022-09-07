package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementGroupDto {
    private Integer id;
    @NotNull(message = "El consecutivo del desembolso es requerido")
    private Integer consecutive;
    @NotNull(message = "La fecha del desembolso es requerido")
    private LocalDate date;
    @NotNull(message = "El proyecto relacionado al grupo de desembolso es requerido")
    private ProjectDto project;
    @NotBlank(message = "El valor total del desembolso es requerido")
    private String totalDisbursement;
    private String totalGmf;
    private String others;
    private Integer spread;
    private LocalDate expirationDate;
    private String obligationNumber;
    private String idOracle;
    private GenericMasterDto periodicityInterest;
    private GenericMasterDto capitalAmortization;
    private boolean disbursementLetterPrinted;
    private FeeDto fee;
    private LocalDate effectiveDate;
    @NotEmpty(message = "El grupo de desembolsos debe contener desembolsos")
    @Valid
    private List<DisbursementDto> disbursementList;
}
