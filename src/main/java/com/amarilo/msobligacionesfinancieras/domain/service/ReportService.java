package com.amarilo.msobligacionesfinancieras.domain.service;

import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ReportService {

    Resource generateDisbursementBankLetter(Integer disbursementId) throws IOException;
}
