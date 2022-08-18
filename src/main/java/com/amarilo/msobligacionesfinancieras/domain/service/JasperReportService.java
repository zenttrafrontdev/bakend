package com.amarilo.msobligacionesfinancieras.domain.service;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Map;

public interface JasperReportService {
    byte[] exportReport(String report,
                        Map<String, Object> parameters,
                        JRBeanCollectionDataSource dataSource);
}
