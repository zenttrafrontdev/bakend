package com.amarilo.msobligacionesfinancieras.domain.service;

import java.util.Map;

public interface JasperReportService {
    byte[] exportReport(String report,
                        Map<String, Object> parameters);
}
