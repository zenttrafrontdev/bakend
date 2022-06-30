package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeItemService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FeeItemRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.fixture.FeeItemFixture.getFeeItemEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class FeeItemServiceImplTest {

    @Autowired
    FeeItemService feeItemService;

    @MockBean
    FeeItemRepository feeItemRepository;

    @Test
    void findById_WithResults() {
        //given
        Optional<FeeItemEntity> feeItemEntityOptional = Optional.of(getFeeItemEntity());
        //when
        when(feeItemRepository.findById(any())).thenReturn(feeItemEntityOptional);
        var result = feeItemService.findById(1);
        //then
        Assertions.assertEquals(feeItemEntityOptional.get().getId(), result.getId());
    }

    @Test
    void findById_WithNoResults() {
        //when
        when(feeItemRepository.findById(any())).thenReturn(Optional.empty());
        //then
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.findById(1));

        Assertions.assertEquals("El periodo de la tasa no existe", thrown.getMessage());
    }

    @Test
    void findAllFeeItemBySearchCriteria_WithoutQueryData() {
        //given
        PageRequestDto<FeeItemSearchCriteria> request = getPageRequestDto(null);
        var item = getFeeItemEntity();
        var list = List.of(item);

        //when
        when(feeItemRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));
        var result = feeItemService.findAllFeeBySearchCriteria(request);

        //then
        Assertions.assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findAllFeeItemBySearchCriteria_WithNoResults() {
        //given
        PageRequestDto<FeeItemSearchCriteria> request = getPageRequestDto(FeeItemSearchCriteria.builder()
                .feeId(1)
                .value("120000000")
                .startDate(LocalDate.of(2022, 01, 01))
                .endDate(LocalDate.of(2022, 01, 31))
                .build());

        //when
        when(feeItemRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = feeItemService.findAllFeeBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }

}