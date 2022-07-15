package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.QuotaSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuotaService {

    PageResponseDto<QuotaDto> findAllQuotasBySearchCriteria(PageRequestDto<QuotaSearchCriteria> pageRequestDto);

    QuotaDto findById(Integer id);

    QuotaDto saveQuota(QuotaDto quotaDto, List<MultipartFile> files);
}
