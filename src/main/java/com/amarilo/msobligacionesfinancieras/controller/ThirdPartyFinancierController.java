package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.domain.dto.ThirdPartyFinancierDto;
import com.amarilo.msobligacionesfinancieras.domain.service.ThirdPartyFinancierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/financial-liabilities/v1/third-party-financier")
public class ThirdPartyFinancierController {
    private final ThirdPartyFinancierService thirdPartyFinancierService;

    @Operation(summary = "Permite obtener los terceros financiadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtienen los terceros financiadores",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ThirdPartyFinancierDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping
    public ResponseEntity<List<ThirdPartyFinancierDto>> findAll() {
        return ResponseEntity.ok(thirdPartyFinancierService.findAll());
    }

    @Operation(summary = "Permite obtener un tercero financiador por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el tercero financiador por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ThirdPartyFinancierDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<ThirdPartyFinancierDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(thirdPartyFinancierService.findById(id));
    }
}
