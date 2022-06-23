package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinanceThirdControllerTest {
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
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/financial-liabilities/v1/finance-third");
    }

    @Test
    void findAllFinanceThird() {
        List<FinanceThirdDto> list = restTemplate.getForObject(baseUrl, List.class);
        Assertions.assertFalse(list.isEmpty());
    }

    @Test
    void findAllFinanceThirdBySearchCriteria() {

        var body = "{\n" +
                "\t\"query\": {\n" +
                "\t\t\"name\": \"a\"\n" +
                "\t},\n" +
                "\t\"page\": 0, \n" +
                "\t\"size\": 10\n" +
                "}";
        HttpEntity<String> request =
                new HttpEntity<>(body, headers);
        PageResponseDto list = restTemplate.postForObject(baseUrl.concat("/search"), request, PageResponseDto.class);
        Assertions.assertFalse(list.getContent().isEmpty());
    }
}