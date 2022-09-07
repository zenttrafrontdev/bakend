package com.amarilo.msobligacionesfinancieras.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotaDto {
    private Integer id;
    @NotNull(message = "La tipología del cupo no puede ser vacío")
    private GenericMasterDto quotaType;
    @NotNull(message = "La clasificación del cupo no puede ser vacío")
    private GenericMasterDto quotaClassification;
    @NotNull(message = "La unidad de negocio no puede ser vacío")
    private BusinessAreaDto businessArea;
    @NotEmpty(message = "El cupo debe ser asignado por lo menos a una etapa")
    private List<QuotaDetailDto> quotaDetails;
    @NotNull(message = "El banco no puede ser vacío")
    private GenericMasterDto bank;
    @NotNull(message = "El tipo de crédito no puede ser vacío")
    private GenericMasterDto creditType;
    @NotNull(message = "La tasa no puede ser vacío")
    private FeeDto fee;
    @NotNull(message = "La fiduciaria no puede ser vacío")
    private FiduciaryDto fiduciary;
    @NotNull(message = "El cupo aprobado no puede ser vacío")
    @NotBlank(message = "El cupo aprobado no puede ser vacío")
    @Size(min = 6, message = "Se requiere un número mínimo seis decímales")
    private String approvedQuota;
    @NotNull(message = "El cupo disponible no puede ser vacío")
    @NotBlank(message = "El cupo disponible no puede ser vacío")
    @Size(min = 6, message = "Se requiere un número mínimo seis decímales")
    private String availableQuota;
    @NotNull(message = "El cupo usado no puede ser vacío")
    @NotBlank(message = "El cupo usado no puede ser vacío")
    @Size(min = 6, message = "Se requiere un número mínimo seis decímales")
    private String quotaUsed;
    @NotNull(message = "El cupo disponible del constructor no puede ser vacío")
    @NotBlank(message = "El cupo disponible del constructor no puede ser vacío")
    @Size(min = 6, message = "Se requiere un número mínimo seis decímales")
    private String builderAvailableQuota;
    @NotNull(message = "La fecha aprobada del cupo no puede ser vacío")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate approvedQuotaDate;
    @NotNull(message = "La fecha de vencimiento del cupo no puede ser vacío")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expirationQuotaDate;
    @NotNull(message = "El plazo del cupo no puede ser vacío")
    private Integer deadLine;
    private List<FileBusinessDto> files;
    private ThirdPartyFinancierDto thirdPartyFinancier;
}
