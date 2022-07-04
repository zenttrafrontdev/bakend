package com.amarilo.msobligacionesfinancieras.controller.request;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeeItemCsvDto {
    @CsvBindByName(column = "NOMBRE_TASA", required = true)
    private String feeName;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "FECHA_INICIO", required = true)
    private LocalDate startDate;
    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByName(column = "FECHA_FIN", required = true)
    private LocalDate endDate;
    @CsvBindByName(column = "VALOR", required = true)
    private String value;
}
