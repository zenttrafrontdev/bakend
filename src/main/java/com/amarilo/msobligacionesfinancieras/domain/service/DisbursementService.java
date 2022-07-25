package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;

public interface DisbursementService {

    PageResponseDto<DisbursementDto> findAllDisbursementsBySearchCriteria(PageRequestDto<DisbursementSearchCriteria> pageRequestDto);

    DisbursementDto findById(Integer id);

    DisbursementDto saveDisbursement(DisbursementDto disbursementDto);
}
