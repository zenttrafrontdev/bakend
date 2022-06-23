package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.FinanceThirdSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FinanceThirdDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FinanceThirdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/financial-liabilities/v1/finance-third")
public class FinanceThirdController {

    private final FinanceThirdService financeThirdService;

    public FinanceThirdController(FinanceThirdService financeThirdService) {
        this.financeThirdService = financeThirdService;
    }

    @Operation(summary = "Permite obtener el listado de terceros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de terceros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FinanceThirdDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping()
    public ResponseEntity<List<FinanceThirdDto>> findAllFinanceThird() {
        return ResponseEntity.ok(financeThirdService.findAllFinanceThird());
    }

    @Operation(summary = "Permite obtener el listado de terceros por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de terceros por filtros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<FinanceThirdDto>> findAllFinanceThirdBySearchCriteria(@RequestBody PageRequestDto<FinanceThirdSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(financeThirdService.findAllFinanceThirdBySearchCriteria(pageRequestDto));
    }
}
