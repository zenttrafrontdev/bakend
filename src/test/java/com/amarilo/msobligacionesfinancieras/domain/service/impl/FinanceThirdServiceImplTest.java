package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FinanceThirdSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FinanceThirdService;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdRepository;
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

import static com.amarilo.msobligacionesfinancieras.fixture.FinanceThirdFixture.getFinanceThirdEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FinanceThirdServiceImplTest {

    @Autowired
    FinanceThirdService financeThirdService;

    @MockBean
    FinanceThirdRepository financeThirdRepository;

    @Test
    void findAllFinanceThird() {
        //given
        var item = getFinanceThirdEntity();
        var list = List.of(item);

        //when
        when(financeThirdRepository.findAll()).thenReturn(list);
        var resultList = financeThirdService.findAllFinanceThird();

        //then
        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(item.getId(), resultList.get(0).getId());
    }

    @Test
    void findAllFinanceThirdBySearchCriteria_WithoutQueryData() {
        //given
        var request = PageRequestDto.<FinanceThirdSearchCriteria>builder()
                .query(null)
                .page(0)
                .size(10)
                .build();
        var item = getFinanceThirdEntity();
        var list = List.of(item);

        //when
        when(financeThirdRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));
        var result = financeThirdService.findAllFinanceThirdBySearchCriteria(request);

        //then
        Assertions.assertFalse(result.getContent().isEmpty());
    }


    @Test
    void findAllFinanceThirdBySearchCriteria_withNoResults() {
        //given
        PageRequestDto<FinanceThirdSearchCriteria> request = getPageRequestDto(FinanceThirdSearchCriteria.builder()
                .name("Jesu")
                .identification("1")
                .contributorId("1")
                .accountNumber("1")
                .status("ACTIVO")
                .taxWithholding("Y")
                .financeThirdTypeId(1)
                .fiscalOrganizationTypeId(1)
                .bankId(1)
                .accountTypeId(1)
                .withholdingTaxGroupId(1)
                .fiscalClassificationId(1)
                .fiscalClassificationTypeId(1)
                .taxClassificationId(1)
                .build());
        var item = getFinanceThirdEntity();
        var list = List.of(item);

        //when
        when(financeThirdRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = financeThirdService.findAllFinanceThirdBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }
}