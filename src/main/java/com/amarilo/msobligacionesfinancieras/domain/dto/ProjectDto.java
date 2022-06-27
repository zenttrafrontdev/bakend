package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProjectDto {

    private Integer id;
    @NotBlank(message = "El código del proyecto no puede ser vacío")
    private String projectCode;
    @NotBlank(message = "El nombre del proyecto no puede ser vacío")
    private String projectName;
    @NotBlank(message = "El código de grupo no puede ser vacío")
    private String groupCode;
    @NotBlank(message = "El nombre de grupo no puede ser vacío")
    private String groupName;
    @NotBlank(message = "El código del consolidador no puede ser vacío")
    private String consolidatorCode;
    @NotBlank(message = "El nombre del consolidador no puede ser vacío")
    private String consolidatorName;
    @NotBlank(message = "El banco del crédito del constructor no puede ser vacío")
    private String buildersCreditBank;
    @NotBlank(message = "El tipo de financiación no puede ser vacío")
    private String paymentType;
    @NotBlank(message = "El estado no puede ser vacío")
    private String status;
}
