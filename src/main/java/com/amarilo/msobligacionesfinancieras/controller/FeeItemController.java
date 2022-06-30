package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.FeeItemSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.FeeItemDto;
import com.amarilo.msobligacionesfinancieras.domain.service.FeeItemService;
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
@RequestMapping("api/financial-liabilities/v1/fee-item")
public class FeeItemController {
    private final FeeItemService feeItemService;

    public FeeItemController(FeeItemService feeItemService) {
        this.feeItemService = feeItemService;
    }

    @Operation(summary = "Permite obtener el listado de periodos de tasas por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de periodos de tasas por filtros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponseFeeItem.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<FeeItemDto>> findAllFeesBySearchCriteria(@RequestBody PageRequestDto<FeeItemSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(feeItemService.findAllFeeItemBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener un periodo de una tasa por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el periodo de una tasa por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FeeItemDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<FeeItemDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(feeItemService.findById(id));
    }

    @Operation(summary = "Permite guardar un periodo de una tasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el periodo exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity saveFeeItem(@Valid @RequestBody FeeItemDto feeItemDto) {
        feeItemService.saveFeeItem(feeItemDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Permite actualizar un periodo de una tasa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado el periodo exitosamente",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("{id}")
    public ResponseEntity updateFeeItem(@Valid @RequestBody FeeItemDto feeItemDto, @PathVariable("id") Integer id) {
        feeItemDto.setId(id);
        feeItemService.updateFeeItem(feeItemDto);
        return ResponseEntity.ok().build();
    }

}

class PageResponseFeeItem extends PageResponseDto<FeeItemDto> { }