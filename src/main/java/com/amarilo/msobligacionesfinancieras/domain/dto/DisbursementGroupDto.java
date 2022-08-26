package com.amarilo.msobligacionesfinancieras.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementGroupDto {
    private Integer id;
    private Integer consecutive;
    private LocalDate date;
    private ProjectDto project;
    private String totalDisbursement;
    private String totalGmf;
    private String others;
    private Integer spread;
    private LocalDate expirationDate;
    private String obligationNumber;
    private String idOracle;
    private GenericMasterDto periodicityInterest;
    private GenericMasterDto capitalAmortization;
    private boolean disbursementLetterPrinted;
    private List<DisbursementDto> disbursementList;
}
