package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.BusinessFileRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.QuotaSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import com.amarilo.msobligacionesfinancieras.domain.mapper.FileBusinessMapper;
import com.amarilo.msobligacionesfinancieras.domain.mapper.QuotaMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.QuotaService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.FileBusinessRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaFileBusinessRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.QuotaRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.FileBusinessEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.QuotaFileBusinessEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.FileBusinessTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.amarilo.msobligacionesfinancieras.commons.Utility.getExtensionByStringHandling;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasApprovedQuotaDate;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasBankId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasBusinessAreaId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasExpirationQuotaDate;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasProjectId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasQuotaClassificationId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.QuotaSpecification.hasQuotaTypeId;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@Slf4j
@Service
public class QuotaServiceImpl implements QuotaService {

    private final QuotaRepository quotaRepository;
    private final QuotaFileBusinessRepository quotaFileBusinessRepository;
    private final FileBusinessRepository fileBusinessRepository;
    private final FileBusinessTypeRepository fileBusinessTypeRepository;

    public QuotaServiceImpl(QuotaRepository quotaRepository,
                            QuotaFileBusinessRepository quotaFileBusinessRepository,
                            FileBusinessRepository fileBusinessRepository,
                            FileBusinessTypeRepository fileBusinessTypeRepository) {
        this.quotaRepository = quotaRepository;
        this.quotaFileBusinessRepository = quotaFileBusinessRepository;
        this.fileBusinessRepository = fileBusinessRepository;
        this.fileBusinessTypeRepository = fileBusinessTypeRepository;
    }

    @Override
    public PageResponseDto<QuotaDto> findAllQuotasBySearchCriteria(PageRequestDto<QuotaSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<QuotaEntity> page;

        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = quotaRepository.findAll(pageable);
        } else {
            page = quotaRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }
        var content = QuotaMapper.INSTANCE.quotaEntityListToQuotaDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    @Override
    public QuotaDto findById(Integer id) {
        var quota = QuotaMapper.INSTANCE.quotaEntityToQuotaDto(quotaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El cupo no existe")));
        var filesList = quotaFileBusinessRepository.findAllByQuotaId(id);
        if (!filesList.isEmpty()) {
            quota.setFiles(filesList.stream()
                    .map(quotaFileBusinessEntity -> FileBusinessMapper.INSTANCE
                            .fileBusinessEntityToFileBusinessDto(quotaFileBusinessEntity.getFileBusiness()))
                    .collect(Collectors.toList()));
        }
        return quota;
    }

    @Transactional
    @Override
    public QuotaDto saveQuota(QuotaDto quotaDto, List<BusinessFileRequestDto> businessFileRequestDtoList, List<MultipartFile> files) throws IOException {
        validateQuotaDates(quotaDto);
        var quotaEntity = QuotaMapper.INSTANCE.quotaDtoToQuotaEntity(quotaDto);
        var quotaSaved = quotaRepository.save(quotaEntity);

        if (Optional.ofNullable(businessFileRequestDtoList).isPresent()
                && !businessFileRequestDtoList.isEmpty()
                && Optional.ofNullable(files).isPresent()
                && !files.isEmpty()
                && businessFileRequestDtoList.size() == files.size()) {
            for (int i = 0; i < businessFileRequestDtoList.size(); i++) {
                log.info(files.get(i).getOriginalFilename());
                log.info(files.get(i).getContentType());
                log.info(String.valueOf(files.get(i).getSize()));
                String extension = getExtensionByStringHandling(files.get(i).getOriginalFilename()).get();


                var fileBusinessEntity = FileBusinessEntity.builder()
                        .fileBusinessType(fileBusinessTypeRepository.findById(businessFileRequestDtoList.get(i).getFileBusinessType().getId())
                                .orElseThrow(() -> new BusinessException("Tipo de archivo de negocio no existe")))
                        .name(UUID.randomUUID().toString().concat(".").concat(extension))
                        .description(businessFileRequestDtoList.get(i).getDescription())
                        .contentType(files.get(i).getContentType())
                        .size(files.get(i).getSize())
                        .url("URL")
                        .build();
                var fileBusinessSaved = fileBusinessRepository.save(fileBusinessEntity);
                quotaFileBusinessRepository.save(QuotaFileBusinessEntity.builder()
                        .quota(quotaSaved)
                        .fileBusiness(fileBusinessSaved)
                        .build());
                Files.copy(files.get(i).getInputStream(), Paths.get(fileBusinessEntity.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
        }


        return QuotaMapper.INSTANCE.quotaEntityToQuotaDto(quotaSaved);
    }

    private void validateQuotaDates(QuotaDto quotaDto) {
        if (quotaDto.getApprovedQuotaDate().isAfter(quotaDto.getExpirationQuotaDate())) {
            throw new BusinessException("La fecha de aprobación del cupo no puede ser mayor a la de vencimiento");
        }
    }

    private Specification<QuotaEntity> getSpecificationFromQuery(QuotaSearchCriteria searchCriteria) {
        Specification<QuotaEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getQuotaTypeId()).isPresent()) {
            specification = buildAndSpecification(null, hasQuotaTypeId(searchCriteria.getQuotaTypeId()));
        }

        if (Optional.ofNullable(searchCriteria.getQuotaClassificationId()).isPresent()) {
            specification = buildAndSpecification(specification, hasQuotaClassificationId(searchCriteria.getQuotaClassificationId()));
        }

        if (Optional.ofNullable(searchCriteria.getBusinessAreaId()).isPresent()) {
            specification = buildAndSpecification(specification, hasBusinessAreaId(searchCriteria.getBusinessAreaId()));
        }

        if (Optional.ofNullable(searchCriteria.getProjectId()).isPresent()) {
            specification = buildAndSpecification(specification, hasProjectId(searchCriteria.getProjectId()));
        }

        if (Optional.ofNullable(searchCriteria.getBankId()).isPresent()) {
            specification = buildAndSpecification(specification, hasBankId(searchCriteria.getBankId()));
        }

        if (Optional.ofNullable(searchCriteria.getApprovedQuotaDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasApprovedQuotaDate(searchCriteria.getApprovedQuotaDate()));
        }

        if (Optional.ofNullable(searchCriteria.getExpirationQuotaDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasExpirationQuotaDate(searchCriteria.getExpirationQuotaDate()));
        }

        return specification;
    }
}
