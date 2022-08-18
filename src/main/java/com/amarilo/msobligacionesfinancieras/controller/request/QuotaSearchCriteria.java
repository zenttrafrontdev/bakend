package com.amarilo.msobligacionesfinancieras.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class QuotaSearchCriteria {
    private Integer quotaTypeId;
    private Integer quotaClassificationId;
    private Integer businessAreaId;
    private Integer projectId;
    private Integer bankId;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate approvedQuotaDate;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate expirationQuotaDate;
}
