package com.amarilo.msobligacionesfinancieras.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FeeSearchCriteria {
    private String name;
    private String periodicity;
    private String valueType;
}
