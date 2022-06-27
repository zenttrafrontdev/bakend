package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;

public interface FeeItemService {
    FeeItemDto findById(Integer id);

    PageResponseDto<FeeItemDto> findAllFeeBySearchCriteria(PageRequestDto<FeeItemSearchCriteria> pageRequestDto);

    void saveFee(FeeItemDto feeItemDto);

    void updateFee(Integer id, FeeItemDto feeItemDto);
}
