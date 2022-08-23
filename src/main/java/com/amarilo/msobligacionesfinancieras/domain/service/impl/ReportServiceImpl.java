package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.domain.service.JasperReportService;
import com.amarilo.msobligacionesfinancieras.domain.service.ReportService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BBVA_BANK_CODE;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BOGOTA_BANK_CODE;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.DAVIVIENDA_BANK_CODE;

@Slf4j
@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final JasperReportService jasperReportService;
    private final DisbursementService disbursementService;

    private static final String BANCOLOMBIA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Bancolombia.pdf";
    private static final String BOGOTA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Bogota.pdf";
    private static final String BBVA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco BBVA.pdf";
    private static final String DAVIVIENDA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Davivienda.pdf";

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
            log.info("Generating disbursement letter for ids -> {}", disbursementIds);
            switch (disbursementDtoList.get(0).getSourceBank().getCode()) {
                case BANCOLOMBIA_BANK_CODE:
                    for (DisbursementDto disbursementDto : disbursementDtoList) {
                        buildLetterWithOneDisbursement(BANCOLOMBIA_BANK_DISBURSEMENT_LETTER_FILENAME, "bancolombia_bank_disbursement_letter.jrxml", disbursementDto.getId(), zipOutputStream);
                    }
                    break;
                case BBVA_BANK_CODE:
                    buildLetterWithMultipleDisbursements(BBVA_BANK_DISBURSEMENT_LETTER_FILENAME, "bbva_bank_disbursement_letter.jrxml", disbursementIds, zipOutputStream);
                    break;
                case BOGOTA_BANK_CODE:
                    buildLetterWithMultipleDisbursements(BOGOTA_BANK_DISBURSEMENT_LETTER_FILENAME, "bogota_bank_disbursement_letter.jrxml", disbursementIds, zipOutputStream);
                    break;
                case DAVIVIENDA_BANK_CODE:
                    buildLetterWithMultipleDisbursements(DAVIVIENDA_BANK_DISBURSEMENT_LETTER_FILENAME, "davivienda_bank_disbursement_letter.jrxml", disbursementIds, zipOutputStream);
                    break;
                default:
                    throw new BusinessException("No existe una carta de desembolso disponible");
            }
            zipOutputStream.close();
        } catch (Exception ex) {

        }
        return new ByteArrayResource(stream.toByteArray()) {
            @Override
            @Nullable
            public String getFilename() {
                return "cartas-desembolsos.pdf";
            }
        };
    }

    private void buildLetterWithOneDisbursement(String fileName, String reportFileName, Integer disbursementId, ZipOutputStream zipOutputStream) throws IOException {
        log.info("Setting parameters- Disbursement id -> {}", disbursementId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "./reports");
        parameters.put("where", String.format("des.id = %s", disbursementId));
        generateReportAndZipIt(fileName, reportFileName, parameters, zipOutputStream);
    }

    private void buildLetterWithMultipleDisbursements(String fileName, String reportFileName, List<Integer> disbursementIds, ZipOutputStream zipOutputStream) throws IOException {
        log.info("Setting parameters- Disbursement ids -> {}", disbursementIds);
        var idList = disbursementIds.stream()
                .map(integer -> String.valueOf(integer))
                .collect(Collectors.toList());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "./reports");
        parameters.put("where", String.format("des.id in (%s)", String.join(",", idList)));
        generateReportAndZipIt(fileName, reportFileName, parameters, zipOutputStream);
    }

    private void generateReportAndZipIt(String fileName, String reportFileName, Map<String, Object> parameters, ZipOutputStream zipOutputStream) throws IOException {
        byte[] report = jasperReportService.exportReport(reportFileName, parameters);
        var file = new File(fileName);
        Files.write(Paths.get(file.getAbsolutePath()), report);
        zipOutputStream.putNextEntry(new ZipEntry(fileName));
        FileInputStream fileInputStream = new FileInputStream(file);
        IOUtils.copy(fileInputStream, zipOutputStream);
        fileInputStream.close();
        Files.delete(Path.of(file.getPath()));
        zipOutputStream.closeEntry();
    }
}
