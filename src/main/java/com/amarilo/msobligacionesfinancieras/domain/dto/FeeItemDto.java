package com.amarilo.msobligacionesfinancieras.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FeeItemDto {
    private Integer id;
    @NotEmpty(message = "El valor no puede ser vacío")
    private String value;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de inicio no puede ser vacía")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de fin no puede ser vacía")
    private LocalDate endDate;
    private FeeDto fee;
}
