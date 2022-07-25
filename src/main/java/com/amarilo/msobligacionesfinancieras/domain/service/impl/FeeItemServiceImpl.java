package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.commons.Utility;
import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemCsvDto;
import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemPeriodRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;
import com.amarilo.msobligacionesfinancieras.domain.enums.PeriodicityEnum;
import com.amarilo.msobligacionesfinancieras.domain.enums.ValueTypeEnum;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FeeItemMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeItemService;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.exception.FileProcessErrorDto;
import com.amarilo.msobligacionesfinancieras.exception.FileProcessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FeeItemRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FeeItemEntity;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.commons.Utility.daysNumberBetweenTwoDates;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasEndDateLessThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasFeeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasStartDateGreaterThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.FeeItemSpecification.hasValue;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Slf4j
@Service
public class FeeItemServiceImpl implements FeeItemService {

    private final FeeItemRepository feeItemRepository;
    private final FeeService feeService;

    public FeeItemServiceImpl(FeeItemRepository feeItemRepository,
                              FeeService feeService) {
        this.feeItemRepository = feeItemRepository;
        this.feeService = feeService;
    }

    private static final String FIELD_VALUE = "valor";
    private static final Long MAX_DAYS_IN_FUTURE_UVR_FEE = 15L;

    @Override
    public FeeItemDto findById(Integer id) {
        return FeeItemMapper.INSTANCE.feeItemEntityToFeeItemDto(feeItemRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El periodo de la tasa no existe")));
    }

    @Override
    public PageResponseDto<FeeItemDto> findAllFeeItemBySearchCriteria(PageRequestDto<FeeItemSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<FeeItemEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = feeItemRepository.findAll(pageable);
        } else {
            page = feeItemRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = FeeItemMapper.INSTANCE.feeItemEntityListToFeeItemDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Transactional
    @Override
    public void saveFeeItem(FeeItemDto feeItemDto) {
        validateValueType(feeItemDto);
        validateDates(feeItemDto.getFee().getId(), feeItemDto.getStartDate(), feeItemDto.getEndDate());
        validatePeriodType(feeItemDto);

        var feeItemEntity = FeeItemMapper.INSTANCE.feeItemDtoToFeeItemEntity(feeItemDto);
        feeItemRepository.save(feeItemEntity);
    }

    @Transactional
    @Override
    public void updateFeeItem(FeeItemPeriodRequestDto feeItemPeriodRequestDto, Integer feeItemId) {
        var feeItemEntity = feeItemRepository.findById(feeItemId)
                .orElseThrow(() -> new BusinessException("El periodo de la tasa que desea actualizar no existe"));
        validateStartDateMustBeBeforeEndDate(feeItemEntity.getStartDate(), feeItemEntity.getEndDate());
        canNotUpdatePeriodicityValueDaysBeforeToday(feeItemEntity.getEndDate());
        feeItemEntity.setValue(feeItemPeriodRequestDto.getValue());
        feeItemRepository.save(feeItemEntity);
    }

    @Transactional
    @Override
    public void processFeeItemCsvFile(MultipartFile file) throws IOException {
        List<FeeItemCsvDto> feeItemCsvDtoList = getFeeItemCsvDtoList(file);
        List<FileProcessErrorDto> errorList = new ArrayList<>();
        for (int i = 0; i < feeItemCsvDtoList.size(); i++) {
            try {
                var feeItemDto = FeeItemDto.builder()
                        .startDate(feeItemCsvDtoList.get(i).getStartDate())
                        .endDate(feeItemCsvDtoList.get(i).getEndDate())
                        .value(feeItemCsvDtoList.get(i).getValue())
                        .fee(feeService.findByName(feeItemCsvDtoList.get(i).getFeeName()))
                        .build();
                saveFeeItem(feeItemDto);
            } catch (Exception ex) {
                log.error(String.format("An error has occurred processing file: Filename: %s, Line Number: %s", file.getOriginalFilename(), i + 1), ex);
                errorList.add(FileProcessErrorDto.builder()
                        .lineNumber(i + 1)
                        .message(ex.getMessage())
                        .build());
            }
        }
        if (!errorList.isEmpty()) {
            throw new FileProcessException("Se han generado errores al procesar el archivo", errorList);
        }

    }

    private List<FeeItemCsvDto> getFeeItemCsvDtoList(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        HeaderColumnNameMappingStrategy<FeeItemCsvDto> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(FeeItemCsvDto.class);

        CsvToBean<FeeItemCsvDto> csvToBean = new CsvToBeanBuilder<FeeItemCsvDto>(fileReader)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .withThrowExceptions(false)
                .build();

        List<FeeItemCsvDto> feeItemCsvDtoList = csvToBean.parse();

        List<FileProcessErrorDto> errors = new ArrayList<>();
        csvToBean.getCapturedExceptions().forEach(exception -> {
            if (exception instanceof CsvRequiredFieldEmptyException) {
                var ex = ((CsvRequiredFieldEmptyException) exception);
                log.error(String.format("Field Empty: Line Number: %s, Record: %s, Field Name: %s",
                                ex.getLineNumber(),
                                Arrays.toString(ex.getLine()),
                                ex.getDestinationField().getName()),
                        exception);
                errors.add(FileProcessErrorDto.builder()
                        .lineNumber(ex.getLineNumber())
                        .line(Arrays.toString(ex.getLine()))
                        .field(ex.getDestinationField().getName())
                        .message("El valor de la columna no puede ser vacío")
                        .build());
            }

            if (exception instanceof CsvDataTypeMismatchException) {
                var ex = ((CsvDataTypeMismatchException) exception);
                log.error(String.format("Field Type Mismatch: Line Number: %s, Record: %s, Value: %s",
                                ex.getLineNumber(),
                                Arrays.toString(ex.getLine()),
                                ex.getSourceObject().toString()),
                        exception);
                errors.add(FileProcessErrorDto.builder()
                        .lineNumber(ex.getLineNumber())
                        .line(Arrays.toString(ex.getLine()))
                        .message(String.format("El valor %s no corresponde al tipo de dato esperado", ex.getSourceObject()))
                        .build());
            }
        });

        if (!errors.isEmpty()) {
            throw new FileProcessException("a", errors);
        }
        return feeItemCsvDtoList;
    }

    /**
     * Válida el tipo de valor: NÚMERICO, PORCENTAJE, Etc.
     *
     * @param feeItemDto
     */
    private void validateValueType(FeeItemDto feeItemDto) {
        var valueType = ValueTypeEnum.getById(feeItemDto.getFee().getValueType());
        switch (valueType) {
            case NUMERIC:
                Utility.validateDecimalField(FIELD_VALUE, feeItemDto.getValue());
                break;
            case PERCENTAGE:
                Long longValue = Utility.validateNumericField(FIELD_VALUE, feeItemDto.getValue());
                Utility.validatePercentageField(FIELD_VALUE, longValue);
                break;
            default:
                break;
        }
    }

    /**
     * Set de validaciones para los tipos de periodo: DIARIO y SEMANAL
     *
     * @param feeItemDto
     */
    private void validatePeriodType(FeeItemDto feeItemDto) {
        var periodType = PeriodicityEnum.getById(feeItemDto.getFee().getPeriodicity());
        feeItemPeriodicityMustBeDaily(feeItemDto, periodType);
        feeItemPeriodicityMustBeWeeklyAndLastPeriodMustEnd(feeItemDto, periodType);
        feeItemDailyPeriodicityCantBeInFuture(feeItemDto, periodType);
        feeItemDailyUVRFeeTypeCanBeUntil15DaysAfterActualDate(feeItemDto);
    }

    /**
     * Válida que cuando la periodicidad sea semanal, solo se puede agregar un nuevo periodo, cuando otro termine
     * Además válida que la semana sea exactamente los siete días
     *
     * @param feeItemDto
     * @param periodType
     */
    private void feeItemPeriodicityMustBeWeeklyAndLastPeriodMustEnd(FeeItemDto feeItemDto, PeriodicityEnum periodType) {
        if (periodType.equals(PeriodicityEnum.WEEKLY)
                && !feeItemDto.getFee().getName().equals("UVR")) {
            var daysBetWeenDays = daysNumberBetweenTwoDates(feeItemDto.getStartDate(), feeItemDto.getEndDate());
            if (daysBetWeenDays != 7) {
                throw new BusinessException("La periodicidad de la tasa debe ser semanal");
            }

            LocalDate maxPeriodDate = feeItemRepository.getMaxPeriodDateByFeeId(feeItemDto.getFee().getId());
            if (Optional.ofNullable(maxPeriodDate).isPresent() && !LocalDate.now().isAfter(maxPeriodDate)) {
                throw new BusinessException("Debe terminar el periodo vigente para poder ingresar un nuevo registro");
            }
        }
    }

    /**
     * Válida que cuando la periodicidad sea periodica y diferente a UVR, solo se permita agregar día a día
     *
     * @param feeItemDto
     * @param periodType
     */
    private void feeItemPeriodicityMustBeDaily(FeeItemDto feeItemDto, PeriodicityEnum periodType) {
        if (periodType.equals(PeriodicityEnum.DAILY)
                && !feeItemDto.getFee().getName().equals("UVR")
                && !feeItemDto.getStartDate().equals(feeItemDto.getEndDate())) {
            throw new BusinessException("La periodicidad de la tasa debe ser diaria");
        }
    }

    /**
     * Válida que para las tasas diferentes a UVR no se permita agregar registros mayores a la fecha actual
     *
     * @param feeItemDto
     * @param periodType
     */
    private void feeItemDailyPeriodicityCantBeInFuture(FeeItemDto feeItemDto, PeriodicityEnum periodType) {
        if (periodType.equals(PeriodicityEnum.DAILY)
                && !feeItemDto.getFee().getName().equals("UVR")
                && feeItemDto.getStartDate().isAfter(LocalDate.now())) {
            throw new BusinessException("No se puede agregar un registro de periodicidad a futuro");
        }
    }

    /**
     * Válida que para la tasa UVR no se permita registrar más de 15 días a futuro con respecto
     * a la fecha actual
     *
     * @param feeItemDto
     */
    private void feeItemDailyUVRFeeTypeCanBeUntil15DaysAfterActualDate(FeeItemDto feeItemDto) {
        if (feeItemDto.getEndDate().isAfter(LocalDate.now())) {
            Long daysFromNowUntilEndDate = daysNumberBetweenTwoDates(LocalDate.now(), feeItemDto.getEndDate());
            Optional<Long> optional = feeItemRepository.getDays(feeItemDto.getFee().getId(), LocalDate.now());
            if (!optional.isPresent()) {
                return;
            }
            Long days = optional.get() + daysNumberBetweenTwoDates(feeItemDto.getStartDate(), feeItemDto.getEndDate());
            if (feeItemDto.getFee().getName().equals("UVR")) {
                if (!days.equals(daysFromNowUntilEndDate)) {
                    throw new BusinessException("Existen periodos sin registrar, no se puede realizar la acción");
                }

                if (daysFromNowUntilEndDate > MAX_DAYS_IN_FUTURE_UVR_FEE) {
                    throw new BusinessException("No se pueden registrar más de 15 días a futuro con respecto a la fecha actual");
                }
            }
        }
    }

    /**
     * Set de validaciones del rango de fechas de un periodo de una tasa
     *
     * @param feeItemId
     * @param startDate
     * @param endDate
     */
    private void validateDates(Integer feeItemId, LocalDate startDate, LocalDate endDate) {
        validateStartDateMustBeBeforeEndDate(startDate, endDate);

        if (!feeItemRepository.validateIfDateRangeIsNotOverlapping(feeItemId, startDate, endDate)) {
            throw new BusinessException("Ya se tiene una tasa para el periodo seleccionado");
        }

        if (!feeItemRepository.validatePeriodDateCanBeLessThanTheLastPeriodDate(feeItemId, endDate)) {
            throw new BusinessException("No se permite guardar registros de fechas pasadas a la ultima vigente.");
        }
    }

    /**
     * Válida que la fecha inicio sea menor o igual a la fecha final
     *
     * @param startDate
     * @param endDate
     */
    private void validateStartDateMustBeBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new BusinessException("El periodo de fecha no es válido");
        }
    }

    /**
     * Válida si la fecha final es menor a la actual, para impedir la actualización de registros
     *
     * @param endDate
     */
    private void canNotUpdatePeriodicityValueDaysBeforeToday(LocalDate endDate) {
        if (endDate.isBefore(LocalDate.now())) {
            throw new BusinessException("No es posible actualizar el valor de periodo para fechas anteriores a la actual");
        }
    }

    /**
     * Se utiliza para filtrar la búsqueda de registros en la base de datos
     *
     * @param searchCriteria
     * @return
     */
    private Specification<FeeItemEntity> getSpecificationFromQuery(FeeItemSearchCriteria searchCriteria) {
        Specification<FeeItemEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getFeeId()).isPresent()) {
            specification = buildAndSpecification(null, hasFeeId(searchCriteria.getFeeId()));
        }

        if (Optional.ofNullable(searchCriteria.getValue()).isPresent()) {
            specification = buildAndSpecification(specification, hasValue(searchCriteria.getValue()));
        }

        if (Optional.ofNullable(searchCriteria.getStartDate()).isPresent() && Optional.ofNullable(searchCriteria.getEndDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasStartDateGreaterThan(searchCriteria.getStartDate()));
            specification = buildAndSpecification(specification, hasEndDateLessThan(searchCriteria.getEndDate()));
        }

        return specification;
    }
}
