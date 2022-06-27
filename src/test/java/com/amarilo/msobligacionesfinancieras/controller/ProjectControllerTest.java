package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.ProjectDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ProjectControllerTest {

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
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/financial-liabilities/v1/project");
    }

    @Test
    void findAllProjectsBySearchCriteria() {
        var body = "{\n" +
                "\t\"query\": {\n" +
                "\t\t\"projectCode\": \"001\"\n" +
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
        ProjectDto projectDto = restTemplate.getForObject(baseUrl.concat("/1"), ProjectDto.class);
        Assertions.assertEquals("001", projectDto.getProjectCode());
        Assertions.assertEquals("PROYECTO NATURA", projectDto.getProjectName());
    }

    @Test
    void saveProjects() {
        var body = "[\n" +
                "    {\n" +
                "    \t\"projectCode\": \"002\",\n" +
                "    \t\"projectName\": \"PROYECTO NUEVO\",\n" +
                "    \t\"groupCode\": \"001\",\n" +
                "    \t\"groupName\": \"GRUPO AMARILO\",\n" +
                "    \t\"consolidatorCode\": \"001\",\n" +
                "    \t\"consolidatorName\": \"CONSOLIDADOR GENERAL\",\n" +
                "    \t\"buildersCreditBank\": \"BBVA\",\n" +
                "    \t\"paymentType\": \"CRÉDITO\",\n" +
                "    \t\"status\": \"ACTIVO\"\n" +
                "    }\n" +
                "]";
        HttpEntity<String> request =
                new HttpEntity<>(body, headers);
        restTemplate.postForObject(baseUrl.concat("/save-all"), request, PageResponseDto.class);
    }
}