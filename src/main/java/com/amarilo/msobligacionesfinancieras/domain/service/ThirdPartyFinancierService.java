package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.domain.dto.ThirdPartyFinancierDto;

import java.util.List;

public interface ThirdPartyFinancierService {

    List<ThirdPartyFinancierDto> findAll();

    ThirdPartyFinancierDto findById(Integer id);
}
