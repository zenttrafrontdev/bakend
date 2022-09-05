package com.amarilo.msobligacionesfinancieras.controller.request;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementGroupPartialRequestDto {
    private Integer consecutive;
    private Integer spread;
    private LocalDate expirationDate;
    private String obligationNumber;
    private GenericMasterDto capitalAmortization;
}
