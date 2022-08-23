package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.service.JasperReportService;
import com.amarilo.msobligacionesfinancieras.exception.BuildReportException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class JasperReportServiceIml implements JasperReportService {

    private final ApplicationContext applicationContext;

    @Override
    public byte[] exportReport(String report,
                               Map<String, Object> parameters) {
        try {
            log.info("Generating report -> {}", report);
            Connection con = DataSourceUtils.getConnection((DataSource) applicationContext.getBean("dataSource"));
            File file = ResourceUtils.getFile("classpath:reports/".concat(report));
            JasperReport jasperReport = null;
            JasperPrint jasperPrint;
            jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
            log.info("Report {} successfully filled", report);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception ex) {
            log.error("An error has occurred generating report {}", report, ex);
            throw new BuildReportException("Ha ocurrido un error generando el reporte");
        }

    }
}
