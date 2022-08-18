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
public class DisbursementSearchCriteria {
    private Integer consecutive;
    private LocalDate date;
    private Integer operationTypeId;
    private Integer projectSourceId;
    private Integer financeThirdId;
    private Integer providerId;
    private Integer paymentProviderId;
    private String disbursementInvoiceNumber;

}
