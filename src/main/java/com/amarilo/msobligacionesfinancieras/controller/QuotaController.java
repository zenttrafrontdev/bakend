package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.BusinessFileRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.QuotaSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.QuotaDto;
import com.amarilo.msobligacionesfinancieras.domain.service.QuotaService;
import com.amarilo.msobligacionesfinancieras.exception.BusinessException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("api/financial-liabilities/v1/quota")
public class QuotaController {

    private final QuotaService quotaService;

    public QuotaController(QuotaService quotaService) {
        this.quotaService = quotaService;
    }

    @Operation(summary = "Permite obtener el listado de cupos por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de cupos por filtros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseQuota.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<QuotaDto>> findAllQuotasBySearchCriteria(@RequestBody PageRequestDto<QuotaSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(quotaService.findAllQuotasBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener un cupo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el cupo por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = QuotaDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<QuotaDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(quotaService.findById(id));
    }

    @Operation(summary = "Permite guardar un cupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupo guardado existosamente",
                    content = {@Content(mediaType = "multipart/form-data", schema = @Schema(implementation = QuotaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Argumentos no válidos",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "multipart/form-data")})
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuotaDto> saveQuota(@RequestParam("quota") String quota,
                                              @RequestParam(value = "business-files", required = false) String businessFileList,
                                              @RequestParam(required = false) List<MultipartFile> files) throws IOException {
        QuotaDto quotaDto = null;
        List<BusinessFileRequestDto> businessFileRequestDtos = new ArrayList<>();
        try {
            var objectMapper = new ObjectMapper().findAndRegisterModules();
            quotaDto = objectMapper.readValue(quota, QuotaDto.class);
            if (businessFileList != null && !businessFileList.isBlank()) {
                businessFileRequestDtos = Arrays.asList(objectMapper.readValue(businessFileList, BusinessFileRequestDto[].class));
            }
        } catch (RuntimeException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        validateDto(quotaDto);
        return ResponseEntity.ok(quotaService.saveQuota(quotaDto, businessFileRequestDtos, files));
    }

    @Operation(summary = "Permite actualizar un cupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupo actualizado existosamente",
                    content = {@Content(mediaType = "multipart/form-data", schema = @Schema(implementation = QuotaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Argumentos no válidos",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "multipart/form-data")})
    })
    @PutMapping(value = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<QuotaDto> updateQuota(@PathVariable("id") Integer id,
                                                @RequestParam("quota") String quota,
                                                @RequestParam(value = "business-files", required = false) String businessFileList,
                                                @RequestParam(required = false) List<MultipartFile> files) throws IOException {
        QuotaDto quotaDto = null;
        List<BusinessFileRequestDto> businessFileRequestDtos = new ArrayList<>();
        try {
            var objectMapper = new ObjectMapper().findAndRegisterModules();
            quotaDto = objectMapper.readValue(quota, QuotaDto.class);
            if (businessFileList != null && !businessFileList.isBlank()) {
                businessFileRequestDtos = Arrays.asList(objectMapper.readValue(businessFileList, BusinessFileRequestDto[].class));
            }
        } catch (RuntimeException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
        quotaDto.setId(id);
        validateDto(quotaDto);
        return ResponseEntity.ok(quotaService.saveQuota(quotaDto, businessFileRequestDtos, files));
    }

    @Operation(summary = "Permite eliminar un archivo de un cupo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Archivo descargado existosamente",
                    content = {@Content(mediaType = "application/octet-stream")}),
            @ApiResponse(responseCode = "400", description = "Argumentos no válidos",
                    content = {@Content(mediaType = "application/octet-stream")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/octet-stream")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/octet-stream")})
    })
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("id") Integer fileBusinessId) throws IOException {
        ByteArrayResource resource = quotaService.downloadFile(fileBusinessId);

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

    private void validateDto(QuotaDto quotaDto) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<QuotaDto>> violations = validator.validate(quotaDto);
        if (!violations.isEmpty()) {
            violations.stream().findFirst().ifPresent(quotaDtoConstraintViolation -> {
                throw new BusinessException(quotaDtoConstraintViolation.getMessage());
            });
        }
    }
}

class PageResponseQuota extends PageResponseDto<QuotaDto> {
}
