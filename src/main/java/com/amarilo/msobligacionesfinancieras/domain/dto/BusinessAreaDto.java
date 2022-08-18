package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusinessAreaDto {
    private Integer id;
    private String acronym;
    private String corporateName;
    private String name;
    private String nit;
    private String estado;
}
