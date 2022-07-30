package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.FiduciaryDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FiduciaryMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FiduciaryService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FiduciaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiduciaryServiceImpl implements FiduciaryService {

    private final FiduciaryRepository fiduciaryRepository;

    public FiduciaryServiceImpl(FiduciaryRepository fiduciaryRepository) {
        this.fiduciaryRepository = fiduciaryRepository;
    }

    @Override
    public List<FiduciaryDto> findAll() {
        return FiduciaryMapper.INSTANCE.fiduciaryEntityListToFiduciaryDtoList(fiduciaryRepository.findAll());
    }
}
