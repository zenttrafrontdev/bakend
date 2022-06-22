package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.FinanceThirdSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;

import java.util.List;

public interface FinanceThirdService {

    List<FinanceThirdDto> findAllFinanceThird();

    PageResponseDto<FinanceThirdDto> findAllFinanceThirdBySearchCriteria(PageRequestDto<FinanceThirdSearchCriteria> pageRequestDto);
}
