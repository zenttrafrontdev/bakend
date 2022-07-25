package com.amarilo.msobligacionesfinancieras.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DisbursementSearchCriteria {
    private Integer projectId;
}
