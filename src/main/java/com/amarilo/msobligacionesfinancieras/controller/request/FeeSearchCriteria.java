package com.amarilo.msobligacionesfinancieras.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FeeSearchCriteria {
    private String name;
    private String periodicity;
    private String valueType;
}
