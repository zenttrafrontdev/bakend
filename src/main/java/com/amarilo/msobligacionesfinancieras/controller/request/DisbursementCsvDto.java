package com.amarilo.msobligacionesfinancieras.controller.request;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DisbursementCsvDto {
    @CsvBindByPosition(position = 0)
    private String operationType;
    @CsvBindByPosition(position = 1)
    private String projectCode;
    @CsvBindByPosition(position = 4, locale = "es-CO")
    @CsvNumber("#.##")
    private BigDecimal disbursementValue;
    @CsvBindByPosition(position = 6)
    private String financeThirdName;
    @CsvBindByPosition(position = 7)
    private String providerName;
    @CsvBindByPosition(position = 8)
    private String fiduciaryName;
    @CsvBindByPosition(position = 9)
    private String source;
    @CsvBindByPosition(position = 10)
    private String amariloConcept;
    @CsvBindByPosition(position = 11)
    private String fiduciaryConcept;
    @CsvBindByPosition(position = 14)
    private String paymentProviderName;
    @CsvBindByPosition(position = 17)
    private String targetAccount;
    @CsvBindByPosition(position = 20)
    private String invoiceNumber;
}
