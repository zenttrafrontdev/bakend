package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessAreaDto {
    private Integer id;
    private String acronym;
    private String corporateName;
    private String name;
    private String nit;
    private String estado;
}
