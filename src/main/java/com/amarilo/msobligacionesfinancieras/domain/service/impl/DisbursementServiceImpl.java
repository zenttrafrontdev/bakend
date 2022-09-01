package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementCsvDto;
import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementGroupSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementGroupDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.DisbursementGroupMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.DisbursementGroupRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.DisbursementRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectFiduciaryRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementGroupEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.AmariloConceptRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.DisbursementOperationRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FiduciaryConceptRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.amarilo.msobligacionesfinancieras.commons.Utility.validateNotNullGreaterThanZeroInteger;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasConsecutive;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasEndDateLessThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasObligationNumber;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasOracleId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasProjectId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementGroupSpecification.hasStartDateGreaterThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Slf4j
@RequiredArgsConstructor
@Service
public class DisbursementServiceImpl implements DisbursementService {

    private final DisbursementGroupRepository disbursementGroupRepository;
    private final DisbursementRepository disbursementRepository;
    private final FinanceThirdRepository financeThirdRepository;
    private final ProjectRepository projectRepository;
    private final DisbursementOperationRepository disbursementOperationRepository;
    private final QuotaRepository quotaRepository;
    private final ProjectFiduciaryRepository projectFiduciaryRepository;
    private final AmariloConceptRepository amariloConceptRepository;
    private final FiduciaryConceptRepository fiduciaryConceptRepository;

    @Override
    public PageResponseDto<DisbursementGroupDto> findAllDisbursementsGroupBySearchCriteria(PageRequestDto<DisbursementGroupSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<DisbursementGroupEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = disbursementGroupRepository.findAll(pageable);
        } else {
            page = disbursementGroupRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }
        var content = DisbursementGroupMapper.INSTANCE.disbursementGroupEntityListToDisbursementGroupDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public DisbursementGroupDto findById(Integer id) {
        return DisbursementGroupMapper.INSTANCE.disbursementGroupEntityToDisbursementGroupDto(disbursementGroupRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El grupo de desembolso no existe")));
    }

    @Override
    public DisbursementGroupDto saveDisbursementGroup(DisbursementGroupDto disbursementDto) {
        DisbursementGroupEntity disbursementGroupEntity = DisbursementGroupMapper.INSTANCE.disbursementGroupDtoToDisbursementGroupEntity(disbursementDto);
        subtractQuotaAvailableAmount(disbursementGroupEntity);
        return DisbursementGroupMapper.INSTANCE.disbursementGroupEntityToDisbursementGroupDto(disbursementGroupRepository.save(disbursementGroupEntity));
    }

    @Override
    public DisbursementGroupDto updateDisbursementGroup(DisbursementGroupDto disbursementDto) {
        DisbursementGroupEntity disbursementGroupEntity = DisbursementGroupMapper.INSTANCE.disbursementGroupDtoToDisbursementGroupEntity(disbursementDto);
        return DisbursementGroupMapper.INSTANCE.disbursementGroupEntityToDisbursementGroupDto(disbursementGroupRepository.save(disbursementGroupEntity));
    }


    private void subtractQuotaAvailableAmount(DisbursementGroupEntity disbursementGroupEntity) {
        disbursementGroupEntity.getDisbursementList().forEach(disbursementEntity -> {
            var quotaEntityOptional = quotaRepository.findById(disbursementEntity.getQuota().getId());
            if (quotaEntityOptional.isPresent()) {
                var quotaEntity = quotaEntityOptional.get();
                var availableQuota = new BigInteger(quotaEntity.getAvailableQuota());
                var disbursementAmount = new BigInteger(disbursementEntity.getValue());
                if (disbursementAmount.compareTo(availableQuota) > 0) {
                    throw new BusinessException("El valor del desembolso supera el valor del cupo");
                }
                var newAvailableQuota = availableQuota.subtract(disbursementAmount);
                quotaEntity.setAvailableQuota(newAvailableQuota.toString());
                disbursementEntity.setQuota(quotaEntity);
            }
        });
    }

    @Override
    public List<DisbursementGroupDto> processDisbursementFile(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        List<DisbursementGroupEntity> disbursementGroupList = new ArrayList<>();
        List<DisbursementCsvDto> disbursementCsvDtoList = new CsvToBeanBuilder(fileReader)
                .withType(DisbursementCsvDto.class)
                .withIgnoreEmptyLine(Boolean.TRUE)
                .withSkipLines(1)
                .withSeparator(';')
                .build()
                .parse();

        var disbursementByProjectCode = disbursementCsvDtoList.stream()
                .filter(x -> "CREDITO".equals(x.getSource()))
                .collect(Collectors.groupingBy(DisbursementCsvDto::getProjectCode));

        for (String projectCode : disbursementByProjectCode.keySet()) {
            var projectEntity = projectRepository.findByProjectCode(projectCode)
                    .orElseThrow(() -> new BusinessException(String.format("Proyecto con código %s no existe!", projectCode)));

            var disbursementList = disbursementByProjectCode.get(projectCode);
            var totalDisbursementValue = disbursementList.stream()
                    .map(DisbursementCsvDto::getDisbursementValue)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO);

            var disbursementGroup = DisbursementGroupEntity.builder()
                    .date(LocalDate.now())
                    .project(projectEntity)
                    .totalDisbursement(totalDisbursementValue.toString())
                    .totalGmf("0")
                    .others("0")
                    .build();

            List<DisbursementEntity> finalDisbursementList = disbursementList.stream()
                    .map(disbursementCsvDto -> {
                        log.info(disbursementCsvDto.getFiduciaryConcept());
                        DisbursementEntity disbursementEntity = DisbursementEntity.builder()
                                .disbursementOperationType(disbursementOperationRepository.findByName(disbursementCsvDto.getOperationType())
                                        .orElseThrow(() -> new BusinessException(String.format("Tipo de operación %s no existe!", disbursementCsvDto.getOperationType()))))
                                .project(projectEntity)
                                .value(disbursementCsvDto.getDisbursementValue().toString())
                                .provider(financeThirdRepository.findByName(disbursementCsvDto.getProviderName())
                                        .orElseThrow(() -> new BusinessException(String.format("El proveedor con nombre %s no existe!", disbursementCsvDto.getProviderName()))))
                                .amariloConcept(amariloConceptRepository.findByName(disbursementCsvDto.getAmariloConcept())
                                        .orElseThrow(() -> new BusinessException(String.format("El concepto de amarilo %s no existe!", disbursementCsvDto.getAmariloConcept()))))
                                .fiduciaryConcept(fiduciaryConceptRepository.findByName(disbursementCsvDto.getFiduciaryConcept())
                                        .orElseThrow(() -> new BusinessException(String.format("El concepto de fiduciaria %s no existe!", disbursementCsvDto.getFiduciaryConcept()))))
                                .paymentProvider(financeThirdRepository.findByName(disbursementCsvDto.getPaymentProviderName())
                                        .orElseThrow(() -> new BusinessException(String.format("El proveedor de pago con nombre %s no existe!", disbursementCsvDto.getPaymentProviderName()))))
                                .targetAccount(disbursementCsvDto.getTargetAccount())
                                .disbursementInvoiceNumber(disbursementCsvDto.getInvoiceNumber())
                                .quota(null)
                                .build();

                        setFinanceThird(disbursementEntity, disbursementCsvDto.getFinanceThirdName());
                        setQuota(disbursementEntity, disbursementCsvDto.getProjectCode());
                        return disbursementEntity;
                    }).collect(Collectors.toList());

            disbursementGroup.setDisbursementList(finalDisbursementList);
            disbursementGroupList.add(disbursementGroup);
        }


        return DisbursementGroupMapper.INSTANCE.disbursementGroupEntityListToDisbursementGroupDtoList(disbursementGroupList);
    }

    private void setFinanceThird(DisbursementEntity disbursementEntity, String financeThirdName) {
        Optional<FinanceThirdEntity> financeThirdEntityOptional = financeThirdRepository.findByName(financeThirdName);
        if (financeThirdEntityOptional.isPresent()) {
            disbursementEntity.setFinanceThird(financeThirdEntityOptional.get());
        } else {
            var projectFinanceThird = projectRepository.findByProjectName(financeThirdName)
                    .orElseThrow(() -> new BusinessException(String.format("El tercero o proyecto %s no existe!", financeThirdName)));
            disbursementEntity.setProjectFinanceThird(projectFinanceThird);
        }
    }

    private void setQuota(DisbursementEntity disbursementEntity, String projectCode) {
        Optional<QuotaEntity> quotaEntityOptional = quotaRepository.findByQuotaDetailsProjectProjectCodeAndWithAvailableQuota(projectCode);
        if (quotaEntityOptional.isPresent()) {
            disbursementEntity.setQuota(quotaEntityOptional.get());
        } else {
            Optional<QuotaEntity> firstQuotaAvailable = quotaRepository.findAllByQuotaDetailsProjectConsolidatorCodeAndAvailableQuotaGreaterThanZero(
                    disbursementEntity.getProject().getConsolidatorCode()).stream().findFirst();
            if (firstQuotaAvailable.isPresent()) {
                disbursementEntity.setQuota(firstQuotaAvailable.get());
            }
        }
    }


    private Specification<DisbursementGroupEntity> getSpecificationFromQuery(DisbursementGroupSearchCriteria searchCriteria) {
        Specification<DisbursementGroupEntity> specification = null;

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getConsecutive())) {
            specification = buildAndSpecification(null, hasConsecutive(searchCriteria.getConsecutive()));
        }

        if (Optional.ofNullable(searchCriteria.getStartDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasStartDateGreaterThan(searchCriteria.getStartDate()));
        }

        if (Optional.ofNullable(searchCriteria.getEndDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasEndDateLessThan(searchCriteria.getEndDate()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getProjectId())) {
            specification = buildAndSpecification(specification, hasProjectId(searchCriteria.getProjectId()));
        }

        if (Optional.ofNullable(searchCriteria.getObligationNumber()).isPresent()) {
            specification = buildAndSpecification(specification, hasObligationNumber(searchCriteria.getObligationNumber()));
        }

        if (Optional.ofNullable(searchCriteria.getOracleId()).isPresent()) {
            specification = buildAndSpecification(specification, hasOracleId(searchCriteria.getOracleId()));
        }

        return specification;
    }
}
