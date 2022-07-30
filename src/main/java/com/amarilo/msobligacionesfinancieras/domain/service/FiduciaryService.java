package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.domain.dto.FiduciaryDto;

import java.util.List;

public interface FiduciaryService {

    List<FiduciaryDto> findAll();
}
