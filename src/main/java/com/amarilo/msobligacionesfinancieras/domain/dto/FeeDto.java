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
public class FeeDto {

    private Integer id;
    @NotBlank(message = "El nombre no puede ser vacío")
    private String name;
    @NotNull(message = "La periodicidad no puede ser vacío")
    private Integer periodicity;
    @NotNull(message = "El tipo de valor no puede ser vacío")
    private Integer valueType;
}
