package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DisbursementService {

    PageResponseDto<DisbursementDto> findAllDisbursementsBySearchCriteria(PageRequestDto<DisbursementSearchCriteria> pageRequestDto);

    DisbursementDto findById(Integer id);

    DisbursementDto saveDisbursement(DisbursementDto disbursementDto);

    List<DisbursementDto> processDisbursementFile(MultipartFile file) throws IOException;
}
