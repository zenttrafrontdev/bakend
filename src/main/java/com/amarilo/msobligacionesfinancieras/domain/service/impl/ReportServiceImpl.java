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
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BANCOLOMBIA_DISBURSEMENT_JASPER_LETTER;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BBVA_BANK_CODE;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BBVA_DISBURSEMENT_JASPER_LETTER;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BOGOTA_BANK_CODE;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BOGOTA_DISBURSEMENT_JASPER_LETTER;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.BOGOTA_DISBURSEMENT_PREOPERATIVE_JASPER_LETTER;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.DAVIVIENDA_BANK_CODE;
import static com.amarilo.msobligacionesfinancieras.commons.Constants.DAVIVIENDA_DISBURSEMENT_JASPER_LETTER;

@Slf4j
@AllArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final JasperReportService jasperReportService;
    private final DisbursementService disbursementService;

    private static final String BANCOLOMBIA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Bancolombia%s.pdf";
    private static final String BOGOTA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Bogota constructor%s.pdf";

    private static final String BOGOTA_BANK_DISBURSEMENT_PREOPERATIVE_LETTER_FILENAME = "Carta desembolsos Banco Bogota%s.pdf";
    private static final String BBVA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco BBVA%s.pdf";
    private static final String DAVIVIENDA_BANK_DISBURSEMENT_LETTER_FILENAME = "Carta desembolsos Banco Davivienda%s.pdf";

    @Override
    public ByteArrayResource generateDisbursementBankLetter(List<Integer> disbursementIds) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(stream);

        disbursementIds.forEach(disbursementId -> {
            var disbursementGroupDto = disbursementService.findById(disbursementId);
            try {
                log.info("Generating disbursement letter for ids -> {}", disbursementId);
                var map = disbursementGroupDto.getDisbursementList().stream()
                        .collect(Collectors.groupingBy(DisbursementDto::getQuota));
                for (var entry : map.entrySet()) {
                    var disbursementListByQuota = entry.getValue();
                    switch (disbursementListByQuota.get(0).getSourceBank().getCode()) {
                        case BANCOLOMBIA_BANK_CODE:
                            for (DisbursementDto disbursementDto : disbursementListByQuota) {
                                buildLetterWithOneDisbursement(String.format(BANCOLOMBIA_BANK_DISBURSEMENT_LETTER_FILENAME,
                                                "_" + disbursementDto.getId().toString()),
                                        BANCOLOMBIA_DISBURSEMENT_JASPER_LETTER,
                                        disbursementDto.getId(), zipOutputStream);
                            }
                            break;
                        case BBVA_BANK_CODE:
                            buildLetterWithMultipleDisbursements(BBVA_BANK_DISBURSEMENT_LETTER_FILENAME,
                                    BBVA_DISBURSEMENT_JASPER_LETTER,
                                    disbursementListByQuota, zipOutputStream);
                            break;
                        case BOGOTA_BANK_CODE:
                            var groupByPreoperative = disbursementListByQuota.stream()
                                    .collect(Collectors.groupingBy(DisbursementDto::isPreoperative));
                            for (var preoperative : groupByPreoperative.entrySet()) {
                                if (Boolean.TRUE.equals(preoperative.getKey())) {
                                    buildLetterWithMultipleDisbursements(BOGOTA_BANK_DISBURSEMENT_PREOPERATIVE_LETTER_FILENAME,
                                            BOGOTA_DISBURSEMENT_PREOPERATIVE_JASPER_LETTER,
                                            preoperative.getValue(), zipOutputStream);
                                } else {
                                    buildLetterWithMultipleDisbursements(BOGOTA_BANK_DISBURSEMENT_LETTER_FILENAME,
                                            BOGOTA_DISBURSEMENT_JASPER_LETTER,
                                            preoperative.getValue(), zipOutputStream);
                                }
                            }
                            break;
                        case DAVIVIENDA_BANK_CODE:
                            buildLetterWithMultipleDisbursements(DAVIVIENDA_BANK_DISBURSEMENT_LETTER_FILENAME,
                                    DAVIVIENDA_DISBURSEMENT_JASPER_LETTER,
                                    disbursementListByQuota, zipOutputStream);
                            break;
                        default:
                            throw new BusinessException("No existe una carta de desembolso disponible");
                    }
                }
            } catch (Exception ex) {
                log.info("Ha ocurrido un error generando la carta de desembolsos. ID Desembolso -> {}", disbursementId, ex);
            }
        });
        zipOutputStream.close();
        return new ByteArrayResource(stream.toByteArray()) {
            @Override
            public String getFilename() {
                return "cartas-desembolsos.zip";
            }
        };
    }

    private void buildLetterWithOneDisbursement(String fileName, String reportFileName,
                                                Integer disbursementId, ZipOutputStream zipOutputStream)
            throws IOException {
        log.info("Setting parameters- Disbursement id -> {}", disbursementId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "reports");
        parameters.put("where", String.format("d.id = %s", disbursementId));
        generateReportAndZipIt(fileName, reportFileName, parameters, zipOutputStream);
    }

    private void buildLetterWithMultipleDisbursements(String fileName, String reportFileName,
                                                      List<DisbursementDto> disbursements, ZipOutputStream zipOutputStream)
            throws IOException {
        var idList = disbursements.stream()
                .map(DisbursementDto::getId)
                .map(String::valueOf)
                .collect(Collectors.toList());
        log.info("Setting parameters- Disbursement ids -> {}", idList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "reports");
        parameters.put("where", String.format("d.id in (%s)", String.join(",", idList)));
        generateReportAndZipIt(String.format(fileName,
                "_" + String.join("-", idList)), reportFileName, parameters, zipOutputStream);
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
