package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.QuotaSearchCriteria;
import com.amarilo.msobligacionesfinancieras.domain.service.QuotaService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FileBusinessRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaFileBusinessRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FileBusinessEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaFileBusinessEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FileBusinessTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static com.amarilo.msobligacionesfinancieras.fixture.QuotaFixture.getQuotaDto;
import static com.amarilo.msobligacionesfinancieras.fixture.QuotaFixture.getQuotaEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class QuotaServiceImplTest {

    @MockBean
    QuotaRepository quotaRepository;
    @MockBean
    QuotaFileBusinessRepository quotaFileBusinessRepository;
    @MockBean
    FileBusinessRepository fileBusinessRepository;
    @MockBean
    FileBusinessTypeRepository fileBusinessTypeRepository;

    @Autowired
    QuotaService quotaService;

    @Test
    void findAllQuotasBySearchCriteria_WithoutQueryData() {
        //given
        PageRequestDto<QuotaSearchCriteria> request = getPageRequestDto(null);
        var item = getQuotaEntity();
        var list = List.of(item);

        //when
        when(quotaRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(list));
        var result = quotaService.findAllQuotasBySearchCriteria(request);

        //then
        Assertions.assertFalse(result.getContent().isEmpty());
    }

    @Test
    void findAllQuotasBySearchCriteria_WithNoResults() {
        //given
        PageRequestDto<QuotaSearchCriteria> request = getPageRequestDto(QuotaSearchCriteria.builder()
                .quotaTypeId(1)
                .quotaClassificationId(1)
                .businessAreaId(1)
                .projectId(1)
                .bankId(1)
                .approvedQuotaDate(LocalDate.of(2022, 01, 01))
                .expirationQuotaDate(LocalDate.of(2022, 01, 31))
                .build());

        //when
        when(quotaRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));
        var result = quotaService.findAllQuotasBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }

    @Test
    void findById_WithNoResults() {
        //when
        when(quotaRepository.findById(any())).thenReturn(Optional.empty());
        //then
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> quotaService.findById(1));

        Assertions.assertEquals("El cupo no existe", thrown.getMessage());
    }

    @Test
    void findById_Ok() {
        //given
        var quotaEntity = getQuotaEntity();
        //when
        when(quotaRepository.findById(any())).thenReturn(Optional.of(quotaEntity));
        when(quotaFileBusinessRepository.findAllByQuotaId(any())).thenReturn(Collections.emptyList());
        var result = quotaService.findById(1);

        //then
        Assertions.assertEquals(quotaEntity.getId(), result.getId());
    }

    @Test
    void findById_WithFiles() {
        //given
        var quotaEntity = getQuotaEntity();
        //when
        when(quotaRepository.findById(any())).thenReturn(Optional.of(quotaEntity));
        when(quotaFileBusinessRepository.findAllByQuotaId(any())).thenReturn(List.of(QuotaFileBusinessEntity.builder()
                        .quota(quotaEntity)
                        .fileBusiness(FileBusinessEntity.builder()
                                .id(1)
                                .name("0686bc20-55f4-484a-9592-a8b6420f05ac.pptx")
                                .description("desc")
                                .contentType("application/vnd.openxmlformats-officedocument.presentationml.presentation")
                                .size(84908L)
                                .url("URL")
                                .build())
                .build()));
        var result = quotaService.findById(1);

        //then
        Assertions.assertEquals(quotaEntity.getId(), result.getId());
        Assertions.assertFalse(result.getFiles().isEmpty());
    }

    @Test
    void saveQuota_WithNoFiles_WrongDates() throws IOException {
        //given
        var quotaDto = getQuotaDto();
        quotaDto.setApprovedQuotaDate(quotaDto.getExpirationQuotaDate().plus(Period.ofDays(1)));
        //when

        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> quotaService.saveQuota(quotaDto, null, null));

        //then
        Assertions.assertEquals("La fecha de aprobación del cupo no puede ser mayor a la de vencimiento", thrown.getMessage());


    }

    @Test
    void saveQuota_WithNoFiles_Ok() throws IOException {
        //given
        var quotaDto = getQuotaDto();

        //when
        when(quotaRepository.save(any())).thenReturn(getQuotaEntity());
        var result = quotaService.saveQuota(quotaDto, null, null);

        //then
        verify(quotaRepository, times(1)).save(any());
        Assertions.assertNotNull(result);

    }
}