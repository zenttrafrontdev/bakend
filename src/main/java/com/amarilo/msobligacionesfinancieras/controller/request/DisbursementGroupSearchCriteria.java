package com.amarilo.msobligacionesfinancieras.controller.request;

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
public class DisbursementGroupSearchCriteria {
    private Integer consecutive;
    private LocalDate date;
    private Integer projectId;
    private String obligationNumber;
    private String oracleId;
}
