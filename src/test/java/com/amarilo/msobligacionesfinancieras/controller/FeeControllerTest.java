package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FeeControllerTest {

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
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/financial-liabilities/v1/fee");
    }

    @Test
    void findAllFeesBySearchCriteria() {
        var body = "{\n" +
                "\t\"query\": {\n" +
                "\t\t\"name\": \"UVR\"\n" +
                "\t},\n" +
                "\t\"page\": 0, \n" +
                "\t\"size\": 10\n" +
                "}";
        HttpEntity<String> request =
                new HttpEntity<>(body, headers);
        PageResponseDto list = restTemplate.postForObject(baseUrl.concat("/search"), request, PageResponseDto.class);
        Assertions.assertFalse(list.getContent().isEmpty());
    }

    @Test
    void findById() {
        FeeDto feeDto = restTemplate.getForObject(baseUrl.concat("/1"), FeeDto.class);
        Assertions.assertEquals("UVR", feeDto.getName());
        Assertions.assertEquals(1, feeDto.getPeriodicity());
        Assertions.assertEquals(1, feeDto.getValueType());
    }

    @Test
    void saveFee() {
        var body = "{\n" +
                "\t\"name\": \"UVR\",\n" +
                "\t\"periodicity\": 1,\n" +
                "\t\"valueType\": 1\n" +
                "}";
        HttpEntity<String> request =
                new HttpEntity<>(body, headers);
        restTemplate.postForObject(baseUrl.concat("/save-all"), request, PageResponseDto.class);
    }

    @Test
    void updateFee() {
        var body = "{\n" +
                "\t\"name\": \"IPC\",\n" +
                "\t\"periodicity\": 2,\n" +
                "\t\"valueType\": 2\n" +
                "}";
        HttpEntity<String> request =
                new HttpEntity<>(body, headers);
        restTemplate.put(baseUrl.concat("/2"), request);
    }
}