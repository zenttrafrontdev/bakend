package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericMasterDto {
    private Integer id;
    private String code;
    private String name;
}
