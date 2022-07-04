package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemPeriodRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FeeItemService {
    FeeItemDto findById(Integer id);

    PageResponseDto<FeeItemDto> findAllFeeItemBySearchCriteria(PageRequestDto<FeeItemSearchCriteria> pageRequestDto);

    void saveFeeItem(FeeItemDto feeItemDto);

    void updateFeeItem(FeeItemPeriodRequestDto feeItemPeriodRequestDto, Integer feeItemId);

    void processFeeItemCsvFile(MultipartFile file) throws IOException;
}
