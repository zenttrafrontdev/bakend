package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeeDto {

    private Integer id;
    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;
    @NotNull(message = "La periodicidad no puede ser vacío")
    private Integer periodicity;
    @NotNull(message = "El tipo de valor no puede ser vacío")
    private Integer valueType;
}
