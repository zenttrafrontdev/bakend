package com.amarilo.msobligacionesfinancieras.domain.service;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PaymentPartialRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PaymentSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.PaymentDto;

public interface PaymentService {
    PaymentDto findById(Integer id);

    PageResponseDto<PaymentDto> findAllPaymentBySearchCriteria(PageRequestDto<PaymentSearchCriteria> pageRequestDto);

    PaymentDto savePayment(PaymentDto paymentDto);

    PaymentDto updatePayment(Integer id, PaymentDto paymentDto);

    void updatePartialPayment(Integer id, PaymentPartialRequestDto paymentDto);

    String getAccountPayableTransactionResponseLog(Integer paymentId);
}
