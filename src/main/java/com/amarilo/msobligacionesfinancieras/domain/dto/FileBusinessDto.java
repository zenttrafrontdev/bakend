package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FileBusinessDto {
    private Integer id;
    private GenericMasterDto fileBusinessType;
    private String name;
    private String description;
}
