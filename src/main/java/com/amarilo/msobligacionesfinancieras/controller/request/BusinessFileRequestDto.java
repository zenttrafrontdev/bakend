package com.amarilo.msobligacionesfinancieras.controller.request;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BusinessFileRequestDto {
    private String description;
    private GenericMasterDto fileBusinessType;
}
