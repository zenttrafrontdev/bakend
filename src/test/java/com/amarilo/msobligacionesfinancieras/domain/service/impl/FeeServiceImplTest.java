package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FeeRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.fixture.FeeFixture.getFeeDto;
import static com.amarilo.msobligacionesfinancieras.fixture.FeeFixture.getFeeDtoWithId;
import static com.amarilo.msobligacionesfinancieras.fixture.FeeFixture.getFeeEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class FeeServiceImplTest {

    @Autowired
    FeeService feeService;

    @MockBean
    FeeRepository feeRepository;

    @Test
    void findById_WithResults() {
        //given
        Optional<FeeEntity> feeEntityOptional = Optional.of(getFeeEntity());
        //when
        when(feeRepository.findById(any())).thenReturn(feeEntityOptional);
        var result = feeService.findById(1);
        //then
        Assertions.assertEquals(feeEntityOptional.get().getId(), result.getId());
    }

    void findById_WithNoResults() {
        //when
        when(feeRepository.findById(any())).thenReturn(Optional.empty());
        //then
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeService.findById(1));

        Assertions.assertEquals("La tasa no existe", thrown.getMessage());
    }

    @Test
    void findAllFeeBySearchCriteria_WithoutQueryData() {
        //given
        PageRequestDto<FeeSearchCriteria> request = getPageRequestDto(null);
        var item = getFeeEntity();
        var list = List.of(item);

        //when
        when(feeRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));
        var result = feeService.findAllFeeBySearchCriteria(request);

        //then
        Assertions.assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findAllFeeBySearchCriteria_WithNoResults() {
        //given
        PageRequestDto<FeeSearchCriteria> request = getPageRequestDto(FeeSearchCriteria.builder()
                .name("UVR")
                .periodicity("1")
                .valueType("1")
                .build());

        //when
        when(feeRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = feeService.findAllFeeBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }

    @Test
    void saveFee() {
        //given
        var fee = getFeeDto();

        //when
        when(feeRepository.save(any())).thenReturn(null);
        feeService.saveFee(fee);

        //then
        verify(feeRepository, times(1)).save(any());
    }

    @Test
    void saveFee_WrongPeriodicity() {
        //given
        var fee = getFeeDto();
        fee.setPeriodicity(0);

        //when
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeService.saveFee(fee));
        //then
        Assertions.assertEquals("Tipo de Periodicidad no válido", thrown.getMessage());
    }

    @Test
    void saveFee_WrongValueType() {
        //given
        var fee = getFeeDto();
        fee.setValueType(0);

        //when
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeService.saveFee(fee));
        //then
        Assertions.assertEquals("Tipo de Valor no válido", thrown.getMessage());
    }

    @Test
    void updateFee_Ok() {
        //given
        var fee = getFeeDtoWithId(1);

        //when
        when(feeRepository.findById(any())).thenReturn(Optional.of(getFeeEntity()));
        when(feeRepository.save(any())).thenReturn(null);
        feeService.updateFee(1, fee);

        //then
        verify(feeRepository, times(1)).save(any());
    }

    @Test
    void updateFee_EntityNotFound() {
        //given
        var fee = getFeeDtoWithId(1);

        //when
        when(feeRepository.findById(any())).thenReturn(Optional.empty());
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeService.updateFee(1, fee));

        //then
        Assertions.assertEquals("La tasa que desea actualizar no existe", thrown.getMessage());
    }
}