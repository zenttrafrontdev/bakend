package com.amarilo.msobligacionesfinancieras.domain.mapper;

import com.amarilo.msobligacionesfinancieras.domain.dto.PaymentDto;
import com.amarilo.msobligacionesfinancieras.infraestructure.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    List<PaymentDto> paymentEntityListToPaymentDtoList(List<PaymentEntity> paymentEntityList);

    PaymentEntity paymentDtoToPaymentEntity(PaymentDto paymentDto);

    PaymentDto paymentEntityToPaymentDto(PaymentEntity paymentEntity);
}
