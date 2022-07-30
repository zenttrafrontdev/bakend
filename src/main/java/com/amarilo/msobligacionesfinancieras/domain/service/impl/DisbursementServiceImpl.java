package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementCsvDto;
import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.DisbursementMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.DisbursementRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.FinanceThirdRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectFiduciaryRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.ProjectRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.DisbursementEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FinanceThirdEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.DisbursementOperationRepository;
import com.opencsv.bean.CsvToBeanBuilder;
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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.amarilo.msobligacionesfinancieras.commons.Utility.validateNotNullGreaterThanZeroInteger;
import static com.amarilo.msobligacionesfinancieras.commons.Utility.validateNotNullNotEmptyString;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasConsecutive;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasDate;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasDisbursementInvoiceNumber;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasFinanceThirdId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasOperationTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasPaymentProviderId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasProjectSourceId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.DisbursementSpecification.hasProviderId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Slf4j
@Service
public class DisbursementServiceImpl implements DisbursementService {

    private final DisbursementRepository disbursementRepository;
    private final FinanceThirdRepository financeThirdRepository;
    private final ProjectRepository projectRepository;
    private final DisbursementOperationRepository disbursementOperationRepository;
    private final QuotaRepository quotaRepository;
    private final ProjectFiduciaryRepository projectFiduciaryRepository;

    public DisbursementServiceImpl(DisbursementRepository disbursementRepository,
                                   FinanceThirdRepository financeThirdRepository,
                                   ProjectRepository projectRepository,
                                   DisbursementOperationRepository disbursementOperationRepository,
                                   QuotaRepository quotaRepository,
                                   ProjectFiduciaryRepository projectFiduciaryRepository) {
        this.disbursementRepository = disbursementRepository;
        this.financeThirdRepository = financeThirdRepository;
        this.projectRepository = projectRepository;
        this.disbursementOperationRepository = disbursementOperationRepository;
        this.quotaRepository = quotaRepository;
        this.projectFiduciaryRepository = projectFiduciaryRepository;
    }

    @Override
    public PageResponseDto<DisbursementDto> findAllDisbursementsBySearchCriteria(PageRequestDto<DisbursementSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<DisbursementEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = disbursementRepository.findAll(pageable);
        } else {
            page = disbursementRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }
        var content = DisbursementMapper.INSTANCE.disbursementEntityListToDisbursementDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public DisbursementDto findById(Integer id) {
        return DisbursementMapper.INSTANCE.disbursementEntityToDisbursementDto(disbursementRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El desembolso no existe")));
    }

    @Override
    public DisbursementDto saveDisbursement(DisbursementDto disbursementDto) {
        DisbursementEntity disbursementEntity = DisbursementMapper.INSTANCE.disbursementDtoToDisbursementEntity(disbursementDto);
        subtractQuotaAvailableAmount(disbursementEntity);
        return DisbursementMapper.INSTANCE.disbursementEntityToDisbursementDto(disbursementRepository.save(disbursementEntity));
    }

    private void subtractQuotaAvailableAmount(DisbursementEntity disbursementEntity) {
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
    }

    @Override
    public List<DisbursementDto> processDisbursementFile(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));

        List<DisbursementCsvDto> disbursementCsvDtoList = new CsvToBeanBuilder(fileReader)
                .withType(DisbursementCsvDto.class)
                .withIgnoreEmptyLine(Boolean.TRUE)
                .withSkipLines(1)
                .withSeparator(';')
                .build()
                .parse();

        List<DisbursementEntity> disbursementList = disbursementCsvDtoList.stream()
                .filter(x -> "CREDITO".equals(x.getSource()))
                .map(disbursementCsvDto -> {
                    DisbursementEntity disbursementEntity = DisbursementEntity.builder()
                            .date(LocalDate.now())
                            .disbursementOperationType(disbursementOperationRepository.findByName(disbursementCsvDto.getOperationType())
                                    .orElseThrow(() -> new BusinessException(String.format("Tipo de operación %s no existe!", disbursementCsvDto.getOperationType()))))
                            .project(projectRepository.findByProjectCode(disbursementCsvDto.getProjectCode())
                                    .orElseThrow(() -> new BusinessException(String.format("Proyecto con código %s no existe!", disbursementCsvDto.getProjectCode()))))
                            .value(disbursementCsvDto.getDisbursementValue().trim().replace(".", ""))
                            .provider(financeThirdRepository.findByName(disbursementCsvDto.getProviderName())
                                    .orElseThrow(() -> new BusinessException(String.format("El proveedor con nombre %s no existe!", disbursementCsvDto.getProviderName()))))
                            .amariloConcept(disbursementCsvDto.getAmariloConcept())
                            .fiduciaryConcept(disbursementCsvDto.getFiduciaryConcept())
                            .paymentProvider(financeThirdRepository.findByName(disbursementCsvDto.getPaymentProviderName())
                                    .orElseThrow(() -> new BusinessException(String.format("El proveedor de pago con nombre %s no existe!", disbursementCsvDto.getPaymentProviderName()))))
                            .targetAccount(disbursementCsvDto.getTargetAccount())
                            .disbursementInvoiceNumber(disbursementCsvDto.getInvoiceNumber())
                            .quota(null)
                            .build();

                    setFinanceThird(disbursementEntity, disbursementCsvDto.getFinanceThirdName());
                    setQuota(disbursementEntity, disbursementCsvDto.getProjectCode());
                    setFiduciary(disbursementEntity);
                    return disbursementEntity;
                }).collect(Collectors.toList());

        return DisbursementMapper.INSTANCE.disbursementEntityListToDisbursementDtoList(disbursementList);
    }

    private void setFiduciary(DisbursementEntity disbursementEntity) {
        var projectFiduciaryOptional = projectFiduciaryRepository.findByProjectProjectCode(disbursementEntity.getProject().getProjectCode());
        if (projectFiduciaryOptional.isPresent()) {
            disbursementEntity.setFiduciary(projectFiduciaryOptional.get().getFiduciary());
        }
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


    private Specification<DisbursementEntity> getSpecificationFromQuery(DisbursementSearchCriteria searchCriteria) {
        Specification<DisbursementEntity> specification = null;

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getConsecutive())) {
            specification = buildAndSpecification(null, hasConsecutive(searchCriteria.getConsecutive()));
        }

        if (Optional.ofNullable(searchCriteria.getDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasDate(searchCriteria.getDate()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getOperationTypeId())) {
            specification = buildAndSpecification(specification, hasOperationTypeId(searchCriteria.getOperationTypeId()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getProjectSourceId())) {
            specification = buildAndSpecification(specification, hasProjectSourceId(searchCriteria.getProjectSourceId()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getFinanceThirdId())) {
            specification = buildAndSpecification(specification, hasFinanceThirdId(searchCriteria.getFinanceThirdId()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getProviderId())) {
            specification = buildAndSpecification(specification, hasProviderId(searchCriteria.getProviderId()));
        }

        if (validateNotNullGreaterThanZeroInteger(searchCriteria.getPaymentProviderId())) {
            specification = buildAndSpecification(specification, hasPaymentProviderId(searchCriteria.getPaymentProviderId()));
        }

        if (validateNotNullNotEmptyString(searchCriteria.getDisbursementInvoiceNumber())) {
            specification = buildAndSpecification(specification, hasDisbursementInvoiceNumber(searchCriteria.getDisbursementInvoiceNumber()));
        }

        return specification;
    }
}
