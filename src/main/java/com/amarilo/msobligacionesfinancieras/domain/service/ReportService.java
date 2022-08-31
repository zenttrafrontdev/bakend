package com.amarilo.msobligacionesfinancieras.domain.service;

import org.springframework.core.io.ByteArrayResource;

import java.io.IOException;
import java.util.List;

public interface ReportService {

    ByteArrayResource generateDisbursementBankLetter(List<Integer> disbursementIds) throws IOException;
}
