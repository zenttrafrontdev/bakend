package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemPeriodRequestDto;
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
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.fixture.FeeFixture.getFeeDtoWithValueType;
import static com.amarilo.msobligacionesfinancieras.fixture.FeeItemFixture.getFeeItemDto;
import static com.amarilo.msobligacionesfinancieras.fixture.FeeItemFixture.getFeeItemDto_WithCustomFeeAndCustomValue;
import static com.amarilo.msobligacionesfinancieras.fixture.FeeItemFixture.getFeeItemEntity;
import static com.amarilo.msobligacionesfinancieras.fixture.PageRequestFixture.getPageRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        var result = feeItemService.findAllFeeItemBySearchCriteria(request);

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
        var result = feeItemService.findAllFeeItemBySearchCriteria(request);

        //then
        Assertions.assertTrue(result.getContent().isEmpty());
    }

    @Test
    void saveFeeItem_ValueTypeNumeric_ValueMustBeNumeric() {
        //given
        var feeItemDto = getFeeItemDto_WithCustomFeeAndCustomValue(getFeeDtoWithValueType(1), "value");

        //when
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("El valor del campo valor debe ser númerico", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValueTypePercentage_ValueMustBeLowOrEqualThan100() {
        //given
        var feeItemDto = getFeeItemDto_WithCustomFeeAndCustomValue(getFeeDtoWithValueType(3), "120000000");

        //when
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("El valor del campo valor no debe ser mayor a 100", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidateRangeDate_StartDateCanNotBeGreaterThanEndDate() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.setStartDate(LocalDate.of(2022, 02, 01));

        //when
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("El periodo de fecha no es válido", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidateRangeDate_ValidateIfDateRangeIsNotOverlapping() {
        //given
        var feeItemDto = getFeeItemDto();

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.FALSE);
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("Ya se tiene una tasa para el periodo seleccionado", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidateRangeDate_ValidatePeriodDateCanBeLessThanTheLastPeriodDate() {
        //given
        var feeItemDto = getFeeItemDto();

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.FALSE);
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("No se permite guardar registros de fechas pasadas a la ultima vigente.", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemPeriodicityMustBeDaily() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.getFee().setName("IPC");

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("La periodicidad de la tasa debe ser diaria", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemPeriodicityMustBeWeeklyAndLastPeriodMustEnd_One() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.getFee().setPeriodicity(2);
        feeItemDto.getFee().setName("IPC");

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("La periodicidad de la tasa debe ser semanal", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemPeriodicityMustBeWeeklyAndLastPeriodMustEnd_Two() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.getFee().setPeriodicity(2);
        feeItemDto.getFee().setName("IPC");
        feeItemDto.setStartDate(LocalDate.of(2022,01,01));
        feeItemDto.setEndDate(LocalDate.of(2022,01,07));
        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.getMaxPeriodDateByFeeId(anyInt())).thenReturn(LocalDate.now().plus(Period.ofDays(1)));
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("Debe terminar el periodo vigente para poder ingresar un nuevo registro", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemDailyPeriodicityCantBeInFuture() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.getFee().setName("IPC");
        feeItemDto.setStartDate(LocalDate.now().plus(Period.ofDays(1)));
        feeItemDto.setEndDate(LocalDate.now().plus(Period.ofDays(1)));

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("No se puede agregar un registro de periodicidad a futuro", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemDailyUVRFeeTypeCanBeUntil15DaysAfterActualDate_One() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.setStartDate(LocalDate.now().plus(Period.ofDays(2)));
        feeItemDto.setEndDate(LocalDate.now().plus(Period.ofDays(3)));

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.getDays(anyInt(), any())).thenReturn(Optional.of(1L));
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("Existen periodos sin registrar, no se puede realizar la acción", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ValidatePeriodType_feeItemDailyUVRFeeTypeCanBeUntil15DaysAfterActualDate_Two() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.setStartDate(LocalDate.now());
        feeItemDto.setEndDate(LocalDate.now().plus(Period.ofDays(15)));

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.getDays(anyInt(), any())).thenReturn(Optional.of(0L));
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.saveFeeItem(feeItemDto));

        //then
        Assertions.assertEquals("No se pueden registrar más de 15 días a futuro con respecto a la fecha actual", thrown.getMessage());
    }

    @Test
    void saveFeeItem_ok() {
        //given
        var feeItemDto = getFeeItemDto();
        feeItemDto.setStartDate(LocalDate.now());
        feeItemDto.setEndDate(LocalDate.now().plus(Period.ofDays(14)));

        //when
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.getDays(anyInt(), any())).thenReturn(Optional.of(0L));
        feeItemService.saveFeeItem(feeItemDto);

        //then
        verify(feeItemRepository, times(1)).save(any());
    }

    @Test
    void updateFeeItem_NotFound() {
        //given
        FeeItemPeriodRequestDto feeItemPeriodRequestDto = FeeItemPeriodRequestDto.builder()
                .value("10")
                .build();

        //when
        when(feeItemRepository.findById(any())).thenReturn(Optional.empty());
        Exception thrown = Assertions.assertThrows(
                BusinessException.class,
                () -> feeItemService.updateFeeItem(feeItemPeriodRequestDto, 1));

        //then
        Assertions.assertEquals("El periodo de la tasa que desea actualizar no existe", thrown.getMessage());
    }

    @Test
    void updateFeeItem_Ok() {
        //given
        var feeItemEntity = getFeeItemEntity();
        FeeItemPeriodRequestDto feeItemPeriodRequestDto = FeeItemPeriodRequestDto.builder()
                .value("10")
                .build();
        feeItemEntity.setStartDate(LocalDate.now());
        feeItemEntity.setEndDate(LocalDate.now().plus(Period.ofDays(14)));

        //when
        when(feeItemRepository.findById(any())).thenReturn(Optional.of(feeItemEntity));
        when(feeItemRepository.validateIfDateRangeIsNotOverlapping(anyInt(), any(), any())).thenReturn(Boolean.TRUE);
        when(feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(anyInt(), any())).thenReturn(Boolean.TRUE);
        feeItemService.updateFeeItem(feeItemPeriodRequestDto, 1);

        //then
        verify(feeItemRepository, times(1)).save(any());
    }
}