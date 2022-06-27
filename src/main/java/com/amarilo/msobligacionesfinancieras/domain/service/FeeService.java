package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;

public interface FeeService {
    FeeDto findById(Integer id);

    PageResponseDto<FeeDto> findAllFeeBySearchCriteria(PageRequestDto<FeeSearchCriteria> pageRequestDto);

    void saveFee(FeeDto feeDto);

    void updateFee(Integer id, FeeDto feeDto);
}
