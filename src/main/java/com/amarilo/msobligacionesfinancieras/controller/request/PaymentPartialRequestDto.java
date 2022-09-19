package com.amarilo.msobligacionesfinancieras.controller.request;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
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
public class PaymentPartialRequestDto {
    private GenericMasterDto paymentStatus;
    private GenericMasterDto accountingStatus;
}
