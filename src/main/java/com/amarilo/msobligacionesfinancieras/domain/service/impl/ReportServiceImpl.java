package com.amarilo.msobligacionesfinancieras.domain.service.impl;

import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import com.amarilo.msobligacionesfinancieras.domain.service.JasperReportService;
import com.amarilo.msobligacionesfinancieras.domain.service.ReportService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public ByteArrayResource generateDisbursementBankLetter(Integer disbursementId) {
        var disbursementGroupDto = disbursementService.findById(disbursementId);
        var disbursementList = disbursementGroupDto.getDisbursementList();
        byte[] file = new byte[0];
        try {
            log.info("Generating disbursement letter for ids -> {}", disbursementId);
            switch (disbursementList.get(0).getQuota().getBank().getCode()) {
                case BANCOLOMBIA_BANK_CODE:
                    for (DisbursementDto disbursementDto : disbursementList) {
                        file = buildLetterWithOneDisbursement(
                                BANCOLOMBIA_DISBURSEMENT_JASPER_LETTER,
                                disbursementDto.getId());
                    }
                    break;
                case BBVA_BANK_CODE:
                    file = buildLetterWithMultipleDisbursements(
                            BBVA_DISBURSEMENT_JASPER_LETTER,
                            disbursementList);
                    break;
                case BOGOTA_BANK_CODE:
                    var groupByPreoperative = disbursementList.stream()
                            .collect(Collectors.groupingBy(DisbursementDto::isPreoperative));
                    for (var preoperative : groupByPreoperative.entrySet()) {
                        if (Boolean.TRUE.equals(preoperative.getKey())) {
                            file = buildLetterWithMultipleDisbursements(
                                    BOGOTA_DISBURSEMENT_PREOPERATIVE_JASPER_LETTER,
                                    preoperative.getValue());
                        } else {
                            file = buildLetterWithMultipleDisbursements(
                                    BOGOTA_DISBURSEMENT_JASPER_LETTER,
                                    preoperative.getValue());
                        }
                    }
                    break;
                case DAVIVIENDA_BANK_CODE:
                    file = buildLetterWithMultipleDisbursements(
                            DAVIVIENDA_DISBURSEMENT_JASPER_LETTER,
                            disbursementList);
                    break;
                default:
                    throw new BusinessException("No existe una carta de desembolso disponible");
            }

        } catch (Exception ex) {
            log.info("Ha ocurrido un error generando la carta de desembolsos. ID Desembolso -> {}", disbursementId, ex);
        }

        return new ByteArrayResource(file) {
            @Override
            public String getFilename() {
                return "cartas-desembolsos.pdf";
            }
        };

    }

    private byte[] buildLetterWithOneDisbursement(String reportFileName,
                                                  Integer disbursementId) {
        log.info("Setting parameters- Disbursement id -> {}", disbursementId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "reports");
        parameters.put("where", String.format("d.id = %s", disbursementId));
        return generateReport(reportFileName, parameters);
    }

    private byte[] buildLetterWithMultipleDisbursements(String reportFileName,
                                                        List<DisbursementDto> disbursements) {
        var idList = disbursements.stream()
                .map(DisbursementDto::getId)
                .map(String::valueOf)
                .collect(Collectors.toList());
        log.info("Setting parameters- Disbursement ids -> {}", idList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ROOT_DIR", "reports");
        parameters.put("where", String.format("d.id in (%s)", String.join(",", idList)));
        return generateReport(reportFileName, parameters);
    }

    private byte[] generateReport(String reportFileName, Map<String, Object> parameters) {
        return jasperReportService.exportReport(reportFileName, parameters);
    }
}
