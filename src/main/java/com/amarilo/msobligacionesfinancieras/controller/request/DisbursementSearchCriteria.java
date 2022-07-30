package com.amarilo.msobligacionesfinancieras.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
