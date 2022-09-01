package com.amarilo.msobligacionesfinancieras.domain.service;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;

public interface ReportService {

    ByteArrayResource generateDisbursementBankLetter(Integer disbursementId) throws IOException;
}
