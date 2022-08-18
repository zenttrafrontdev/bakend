package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementBankLetterDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.domain.service.JasperReportService;
import com.amarilo.msobligacionesfinancieras.domain.service.ReportService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.amarilo.msobligacionesfinancieras.commons.Constants.BANCOLOMBIA_BANK_CODE;

@Slf4j
@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final JasperReportService jasperReportService;
    private final DisbursementService disbursementService;

    @Override
    public ByteArrayResource generateDisbursementBankLetter(List<Integer> disbursementIds) {
        if (disbursementIds.isEmpty()) {
            throw new BusinessException("No hay desembolsos a los cuales generar cartas");
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(stream);

        try {
            List<DisbursementDto> disbursementDtoList = disbursementIds.stream()
                    .map(disbursementService::findById)
                    .collect(Collectors.toList());

            switch (disbursementDtoList.get(0).getSourceBank().getCode()) {
                case BANCOLOMBIA_BANK_CODE:
                    //disbursementDtoList.forEach();
                    break;
                default:
                    buildDefaultLetter(disbursementDtoList, zipOutputStream);
                    break;
            }
            zipOutputStream.close();
        } catch (Exception ex) {

        }
        return new ByteArrayResource(stream.toByteArray()) {
            @Override
            @Nullable
            public String getFilename() {
                return "file.pdf";
            }
        };
    }

    private void buildDefaultLetter(List<DisbursementDto> disbursementDtoList, ZipOutputStream zipOutputStream) throws IOException {
        var disbursementBankLetterDtoList = disbursementDtoList.stream().map(disbursementDto -> DisbursementBankLetterDto.builder()
                .value(disbursementDto.getValue())
                .beneficiary(disbursementDto.getFinanceThird().getName())
                .ownerIdentification(disbursementDto.getFinanceThird().getIdentification())
                .build()).collect(Collectors.toList());
        log.info(disbursementBankLetterDtoList.toString());
        var disbursement = disbursementDtoList.get(0);
        var disbursementSumValue = disbursementDtoList
                .stream()
                .map(disbursementDto -> new BigInteger(disbursementDto.getValue()))
                .reduce(BigInteger.ZERO, BigInteger::add);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("banco", disbursement.getSourceBank().getName());
        parameters.put("destinatario", "JESUS VILLALBA");
        parameters.put("proyecto", disbursement.getProject().getProjectName());
        parameters.put("valor_desembolso", disbursementSumValue.toString());
        parameters.put("valor_gmf", "0");
        parameters.put("direccion", "ditaires");
        parameters.put("contacto", "jesus");
        parameters.put("ROOT_DIR", "./reports");
        generateReportAndZipIt(parameters, zipOutputStream, disbursementBankLetterDtoList);
    }

    private void generateReportAndZipIt(Map<String, Object> parameters, ZipOutputStream zipOutputStream, List<DisbursementBankLetterDto> dataSource) throws IOException {
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(dataSource, false);
        byte[] report = jasperReportService.exportReport("davivienda.jrxml", parameters, jrBeanCollectionDataSource);
        var file = new File(buildFileName());
        Files.write(Paths.get(file.getAbsolutePath()), report);
        zipOutputStream.putNextEntry(new ZipEntry(buildFileName()));
        FileInputStream fileInputStream = new FileInputStream(file);
        IOUtils.copy(fileInputStream, zipOutputStream);
        fileInputStream.close();
        Files.delete(Path.of(file.getPath()));
        zipOutputStream.closeEntry();
    }

    private String buildFileName() {
        return "test.pdf";
    }
}
