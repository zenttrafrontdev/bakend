package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PaymentPartialRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PaymentSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.PaymentDto;
import com.amarilo.msobligacionesfinancieras.domain.enums.AppEventProcessEnum;
import com.amarilo.msobligacionesfinancieras.domain.enums.AppEventTypeEnum;
import com.amarilo.msobligacionesfinancieras.domain.mapper.GenericMasterMapper;
import com.amarilo.msobligacionesfinancieras.domain.mapper.PaymentMapper;
import com.amarilo.msobligacionesfinancieras.domain.service.AppEventService;
import com.amarilo.msobligacionesfinancieras.domain.service.PaymentService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.amarilo.msobligacionesfinancieras.infraestructure.DisbursementGroupRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.PaymentRepository;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.AppEventEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.PaymentEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.AccountingStatusEntity;
import com.amarilo.msobligacionesfinancieras.infraestructure.generic.entity.PaymentStatusEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.PaymentSpecification.hasDisbursementGroupByObligationNumber;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.PaymentSpecification.hasEndDateLessThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.PaymentSpecification.hasStartDateGreaterThan;
import static com.amarilo.msobligacionesfinancieras.infraestructure.specification.SpecificationUtils.buildAndSpecification;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final DisbursementGroupRepository disbursementGroupRepository;
    private final AppEventService appEventService;

    @Override
    public PaymentDto findById(Integer id) {
        return PaymentMapper.INSTANCE.paymentEntityToPaymentDto(paymentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El pago no existe")));
    }

    @Override
    public PageResponseDto<PaymentDto> findAllPaymentBySearchCriteria(PageRequestDto<PaymentSearchCriteria> pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<PaymentEntity> page;
        if (!Optional.ofNullable(pageRequestDto.getQuery()).isPresent()) {
            page = paymentRepository.findAll(pageable);
        } else {
            page = paymentRepository.findAll(getSpecificationFromQuery(pageRequestDto.getQuery()), pageable);
        }

        var content = PaymentMapper.INSTANCE.paymentEntityListToPaymentDtoList(page.getContent());
        return new PageResponseDto<>(content, pageable.getPageNumber(), pageable.getPageSize(), page.getTotalElements());
    }

    private void setTotalPaymentValue(PaymentEntity paymentEntity) {
        BigInteger totalValue = new BigInteger("0");
        if (paymentEntity.getCapital().isBlank()) {
            totalValue.add(new BigInteger(paymentEntity.getCapital()));
        }

        if (paymentEntity.getRates().isBlank()) {
            totalValue.add(new BigInteger(paymentEntity.getRates()));
        }

        if (paymentEntity.getPaymentDetailOtherConcepts() != null && !paymentEntity.getPaymentDetailOtherConcepts().isEmpty()) {
            paymentEntity.getPaymentDetailOtherConcepts().forEach(x -> totalValue.add(new BigInteger(x.getValue())));
        }

        paymentEntity.setTotalValue(totalValue.toString());
    }

    @Transactional
    @Override
    public PaymentDto savePayment(PaymentDto paymentDto) {
        var paymentEntity = PaymentMapper.INSTANCE.paymentDtoToPaymentEntity(paymentDto);
        setTotalPaymentValue(paymentEntity);
        var paymentSaved = paymentRepository.save(paymentEntity);
        sentPaymentAccountPayableMigration(paymentSaved);
        return PaymentMapper.INSTANCE.paymentEntityToPaymentDto(paymentSaved);
    }

    @Transactional
    @Override
    public PaymentDto updatePayment(Integer id, PaymentDto paymentDto) {
        var paymentEntity = PaymentMapper.INSTANCE.paymentDtoToPaymentEntity(paymentDto);
        paymentEntity.setId(id);
        paymentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El pago que desea actualizar no existe"));
        setTotalPaymentValue(paymentEntity);
        return PaymentMapper.INSTANCE.paymentEntityToPaymentDto(paymentRepository.save(paymentEntity));
    }

    @Override
    public void updatePartialPayment(Integer id, PaymentPartialRequestDto paymentPartialRequestDto) {
        var paymentEntity = paymentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("El pago que desea actualizar no existe"));

        if(Optional.ofNullable(paymentPartialRequestDto.getPaymentStatus()).isPresent()){
            paymentEntity.setPaymentStatus((PaymentStatusEntity) GenericMasterMapper.INSTANCE.genericMasterDtoToGenericMaster(paymentPartialRequestDto.getPaymentStatus()));
        }

        if(Optional.ofNullable(paymentPartialRequestDto.getAccountingStatus()).isPresent()){
            paymentEntity.setAccountingStatus((AccountingStatusEntity) GenericMasterMapper.INSTANCE.genericMasterDtoToGenericMaster(paymentPartialRequestDto.getAccountingStatus()));
        }
        paymentRepository.save(paymentEntity);
    }

    @Override
    public String getAccountPayableTransactionResponseLog(Integer paymentId) {
        return appEventService.getLogs(paymentId.toString(), AppEventTypeEnum.MIGRATIONS.getValue(), AppEventProcessEnum.ACCOUNT_PAYABLE.getValue());
    }

    private Specification<PaymentEntity> getSpecificationFromQuery(PaymentSearchCriteria searchCriteria) {
        Specification<PaymentEntity> specification = null;

        if (Optional.ofNullable(searchCriteria.getObligationNumber()).isPresent()) {
            var disbursementGroupOptional = disbursementGroupRepository.findByObligationNumber(searchCriteria.getObligationNumber());
            if (disbursementGroupOptional.isPresent()) {
                specification = buildAndSpecification(null, hasDisbursementGroupByObligationNumber(disbursementGroupOptional.get().getId()));
            }
        }

        if (Optional.ofNullable(searchCriteria.getStartDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasStartDateGreaterThan(searchCriteria.getStartDate()));
        }

        if (Optional.ofNullable(searchCriteria.getEndDate()).isPresent()) {
            specification = buildAndSpecification(specification, hasEndDateLessThan(searchCriteria.getEndDate()));
        }
        return specification;
    }

    private void sentPaymentAccountPayableMigration(PaymentEntity paymentEntity) {
        paymentEntity.setOracleId("000001");
        paymentRepository.save(paymentEntity);
        saveExternalTransactionResponse(paymentEntity.getId(), "Transacción exitosa");
    }

    private void saveExternalTransactionResponse(Integer paymentId, String log) {
        appEventService.saveAppEvent(AppEventEntity.builder()
                .eventType(AppEventTypeEnum.MIGRATIONS.getValue())
                .eventDate(LocalDateTime.now())
                .process(AppEventProcessEnum.ACCOUNT_PAYABLE.getValue())
                .recordId(paymentId.toString())
                .comments("Se realiza comunicación con la integración de cuentas por pagar")
                .log(log)
                .build());
    }
}
