package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GenericControllerTest {
    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;
    private static HttpHeaders headers = null;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/financial-liabilities/v1/generic/");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "finance-third-types",
            "fiscal-organization-types",
            "banks",
            "account-types",
            "withholding-tax-groups",
            "fiscal-classifications",
            "fiscal-classification-types",
            "tax-classifications"
    })
    void testGenericMasters(String arg) {
        List<GenericMasterDto> list = restTemplate.getForObject(baseUrl.concat(arg), List.class);
        Assertions.assertFalse(list.isEmpty());
    }
}