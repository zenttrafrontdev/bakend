package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FinanceThirdMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FinanceThirdService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceThirdServiceImpl implements FinanceThirdService {

    private final FinanceThirdRepository financeThirdRepository;

    public FinanceThirdServiceImpl(FinanceThirdRepository financeThirdRepository) {
        this.financeThirdRepository = financeThirdRepository;
    }

    @Override
    public List<FinanceThirdDto> findAllFinanceThird() {
        return FinanceThirdMapper.INSTANCE.financeThirdListToFinanceThirdDtoList(financeThirdRepository.findAll());
    }
}
