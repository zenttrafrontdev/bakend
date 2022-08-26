package com.amarilo.msobligacionesfinancieras.domain.service;

import org.springframework.core.io.ByteArrayResource;

public interface ReportService {

    ByteArrayResource generateDisbursementBankLetter(Integer disbursementId);
}
