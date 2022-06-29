package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("api/financial-liabilities/v1/fee")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @Operation(summary = "Permite obtener el listado de tasas por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tasas por filtros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseFee.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<FeeDto>> findAllFeesBySearchCriteria(@RequestBody PageRequestDto<FeeSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(feeService.findAllFeeBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener una tasa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene la tasa por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FeeDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<FeeDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(feeService.findById(id));
    }

    @Operation(summary = "Permite guardar una tasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado la tasa exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity saveFee(@Valid @RequestBody FeeDto feeDto) {
        feeService.saveFee(feeDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Permite actualizar una tasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado la tasa exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("{id}")
    public ResponseEntity updateFee(@Valid @RequestBody FeeDto feeDto, @PathVariable Integer id) {
        feeService.updateFee(id, feeDto);
        return ResponseEntity.ok().build();
    }
}

class PageResponseFee extends PageResponseDto<FeeDto> { }