package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.service.JasperReportService;
import com.amarilo.msobligacionesfinancieras.exception.BuildReportException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class JasperReportServiceIml implements JasperReportService {

    @Override
    public byte[] exportReport(String report,
                               Map<String, Object> parameters,
                               JRBeanCollectionDataSource dataSource) {
        try {
            File file = ResourceUtils.getFile("classpath:reports/".concat(report));
            JasperReport jasperReport = null;
            JasperPrint jasperPrint;
            if (Optional.of(dataSource).isPresent()) {
                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            } else {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
            }
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception ex) {
            log.error("An error has occurred generating report {}", report, ex);
            throw new BuildReportException("Ha ocurrido un error generando el reporte");
        }

    }
}
