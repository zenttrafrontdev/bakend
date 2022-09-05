package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementGroupPartialRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementGroupSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementGroupDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DisbursementService {

    PageResponseDto<DisbursementGroupDto> findAllDisbursementsGroupBySearchCriteria(PageRequestDto<DisbursementGroupSearchCriteria> pageRequestDto);

    DisbursementGroupDto findById(Integer id);

    DisbursementGroupDto saveDisbursementGroup(DisbursementGroupDto disbursementGroupDto);

    DisbursementGroupDto updateDisbursementGroup(DisbursementGroupDto disbursementGroupDto);

    DisbursementGroupDto partialUpdateDisbursementGroup(Integer id, DisbursementGroupPartialRequestDto disbursementGroupPartialRequestDto);

    List<DisbursementGroupDto> processDisbursementFile(MultipartFile file) throws IOException;
}
