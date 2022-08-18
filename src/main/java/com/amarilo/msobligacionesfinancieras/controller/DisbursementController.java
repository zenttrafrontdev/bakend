package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.DisbursementSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.DisbursementDto;
import com.amarilo.msobligacionesfinancieras.domain.service.DisbursementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/financial-liabilities/v1/disbursement")
public class DisbursementController {

    private final DisbursementService disbursementService;

    public DisbursementController(DisbursementService disbursementService) {
        this.disbursementService = disbursementService;
    }

    @Operation(summary = "Permite obtener el listado de desembolsos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de desembolsos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseDisbursement.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<DisbursementDto>> findAllDisbursementsBySearchCriteria(@RequestBody PageRequestDto<DisbursementSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(disbursementService.findAllDisbursementsBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener un desembolso por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el desembolso por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = DisbursementDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<DisbursementDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(disbursementService.findById(id));
    }

    @Operation(summary = "Permite guardar un desembolso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el desembolso exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<DisbursementDto> saveDisbursement(@RequestBody @Valid DisbursementDto disbursementDto) {
        return ResponseEntity.ok(disbursementService.saveDisbursement(disbursementDto));
    }

    @Operation(summary = "Permite generar unos desembolsos a partir de un archivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desembolsos guardado existosamente",
                    content = {@Content(mediaType = "multipart/form-data", schema = @Schema(implementation = DisbursementDto.class))}),
            @ApiResponse(responseCode = "400", description = "Argumentos no válidos",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "multipart/form-data")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "multipart/form-data")})
    })
    @PostMapping(value = "process-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<DisbursementDto>> processDisbursementFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(disbursementService.processDisbursementFile(file));
    }
}

class PageResponseDisbursement extends PageResponseDto<DisbursementDto> {
}
