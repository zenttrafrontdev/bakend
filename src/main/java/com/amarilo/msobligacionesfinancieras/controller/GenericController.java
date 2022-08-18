package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.domain.dto.BusinessAreaDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.GenericMasterDto;
import com.amarilo.msobligacionesfinancieras.domain.service.GenericService;
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
@RequestMapping("api/financial-liabilities/v1/generic")
public class GenericController {

    private final GenericService genericService;

    public GenericController(GenericService genericService) {
        this.genericService = genericService;
    }

    @Operation(summary = "Permite obtener el listado de tipos de terceros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de terceros",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("finance-third-types")
    public ResponseEntity<List<GenericMasterDto>> findAllFinanceThirdTypes() {
        return ResponseEntity.ok(genericService.findAllFinanceThirdTypes());
    }

    @Operation(summary = "Permite obtener el listado de tipos de organización fiscal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de organización fiscal",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("fiscal-organization-types")
    public ResponseEntity<List<GenericMasterDto>> findAllFiscalOrganizationTypes() {
        return ResponseEntity.ok(genericService.findAllFiscalOrganizationTypes());
    }

    @Operation(summary = "Permite obtener el listado de bancos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de bancos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("banks")
    public ResponseEntity<List<GenericMasterDto>> findAllBanks() {
        return ResponseEntity.ok(genericService.findAllBanks());
    }

    @Operation(summary = "Permite obtener el listado de tipos de cuentas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de cuentas",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("account-types")
    public ResponseEntity<List<GenericMasterDto>> findAllAccountTypes() {
        return ResponseEntity.ok(genericService.findAllAccountTypes());
    }

    @Operation(summary = "Permite obtener el listado de grupos de retención de impuestos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de grupos de retención de impuestos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("withholding-tax-groups")
    public ResponseEntity<List<GenericMasterDto>> findAllWithholdingTaxGroups() {
        return ResponseEntity.ok(genericService.findAllWithholdingTaxGroups());
    }

    @Operation(summary = "Permite obtener el listado de clasificaciones fiscales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de clasificaciones fiscales",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("fiscal-classifications")
    public ResponseEntity<List<GenericMasterDto>> findAllFiscalClassifications() {
        return ResponseEntity.ok(genericService.findAllFiscalClassifications());
    }

    @Operation(summary = "Permite obtener el listado de tipos de clasificaciones fiscales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de clasificaciones fiscales",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("fiscal-classification-types")
    public ResponseEntity<List<GenericMasterDto>> findAllFiscalClassificationTypes() {
        return ResponseEntity.ok(genericService.findAllFiscalClassificationTypes());
    }

    @Operation(summary = "Permite obtener el listado de clasificaciones impuestos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de clasificaciones impuestos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("tax-classifications")
    public ResponseEntity<List<GenericMasterDto>> findAllTaxClassification() {
        return ResponseEntity.ok(genericService.findAllTaxClassifications());
    }

    @Operation(summary = "Permite obtener el listado de unidades de negocio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de unidades de negocio",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BusinessAreaDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("business-areas")
    public ResponseEntity<List<BusinessAreaDto>> findAllBusinessAreas() {
        return ResponseEntity.ok(genericService.findAllBusinessAreas());
    }

    @Operation(summary = "Permite obtener el listado de tipologias de cupos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipologias de cupos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("quota-types")
    public ResponseEntity<List<GenericMasterDto>> findAllQuotaTypes() {
        return ResponseEntity.ok(genericService.findAllQuotaTypes());
    }

    @Operation(summary = "Permite obtener el listado de clasificación de cupos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de clasificación de cupos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("quota-classifications")
    public ResponseEntity<List<GenericMasterDto>> findAllQuotaClassifications() {
        return ResponseEntity.ok(genericService.findAllQuotaClassifications());
    }

    @Operation(summary = "Permite obtener el listado de periodicidades de interes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de periodicidades de interes",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("periodicity-interests")
    public ResponseEntity<List<GenericMasterDto>> findAllPeriodicityInterests() {
        return ResponseEntity.ok(genericService.findAllPeriodicityInterests());
    }

    @Operation(summary = "Permite obtener el listado de tipos de crédito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de crédito",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("credit-types")
    public ResponseEntity<List<GenericMasterDto>> findAllCreditTypes() {
        return ResponseEntity.ok(genericService.findAllCreditTypes());
    }

    @Operation(summary = "Permite obtener el listado de amortización de capitales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de amortización de capitales",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("capital-amortizations")
    public ResponseEntity<List<GenericMasterDto>> findAllCapitalAmortizations() {
        return ResponseEntity.ok(genericService.findAllCapitalAmortizations());
    }

    @Operation(summary = "Permite obtener el listado de tipos de archivo del negocio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de tipos de archivo del negocio",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("file-business-types")
    public ResponseEntity<List<GenericMasterDto>> findAllFileBusinessTypes() {
        return ResponseEntity.ok(genericService.findAllFileBusinessTypes());
    }

    @Operation(summary = "Permite obtener el listado de conceptos de Amarilo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de conceptos de Amarilo",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("amarilo-concepts")
    public ResponseEntity<List<GenericMasterDto>> findAllAmariloConcepts() {
        return ResponseEntity.ok(genericService.findAllAmariloConcepts());
    }

    @Operation(summary = "Permite obtener el listado de conceptos de Fiducia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de conceptos de fiducia",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GenericMasterDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("fiduciary-concepts")
    public ResponseEntity<List<GenericMasterDto>> findAllFiduciaryConcepts() {
        return ResponseEntity.ok(genericService.findAllFiduciaryConcepts());
    }
}
