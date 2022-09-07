package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.ThirdPartyFinancierDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.ThirdPartyFinancierMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.ThirdPartyFinancierService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.ThirdPartyFinancierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ThirdPartyFinancierServiceImpl implements ThirdPartyFinancierService {

    private final ThirdPartyFinancierRepository thirdPartyFinancierRepository;

    @Override
    public List<ThirdPartyFinancierDto> findAll() {
        return ThirdPartyFinancierMapper.INSTANCE.thirdPartyFinancierEntityListToThirdPartyFinancierDtoList(thirdPartyFinancierRepository.findAll());
    }

    @Override
    public ThirdPartyFinancierDto findById(Integer id) {
        return ThirdPartyFinancierMapper.INSTANCE.thirdPartyFinancierEntityToThirdPartyFinancierDto(
                thirdPartyFinancierRepository.findById(id)
                        .orElseThrow(() -> new BusinessException("Tercero financiador no existe!")));
    }
}
