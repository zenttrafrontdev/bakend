package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.domain.dto.FiduciaryDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FiduciaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/financial-liabilities/v1/fiduciary")
public class FiduciaryController {

    private final FiduciaryService fiduciaryService;

    public FiduciaryController(FiduciaryService fiduciaryService) {
        this.fiduciaryService = fiduciaryService;
    }

    @Operation(summary = "Permite obtener un listado de fiduciarias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de fiduciarias",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FiduciaryDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping
    public ResponseEntity<List<FiduciaryDto>> findAll() {
        return ResponseEntity.ok(fiduciaryService.findAll());
    }
}
