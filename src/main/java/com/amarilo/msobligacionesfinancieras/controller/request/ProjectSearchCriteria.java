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
public class ProjectSearchCriteria {
    private String projectCode;
    private String projectName;
    private String groupCode;
    private String groupName;
    private String consolidatorCode;
    private String consolidatorName;
    private String buildersCreditBank;
    private String paymentType;
    private String status;
}
