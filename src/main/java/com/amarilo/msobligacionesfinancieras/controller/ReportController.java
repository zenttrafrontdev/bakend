package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.domain.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/financial-liabilities/v1/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("disbursement-bank-letter")
    public ResponseEntity<Resource> generateDisbursementBankLetter(
            @RequestParam("disbursementIds") List<Integer> disbursementIds
    ) throws IOException {
        ByteArrayResource resource = reportService.generateDisbursementBankLetter(disbursementIds);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }
}
