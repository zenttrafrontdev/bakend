package com.amarilo.msobligacionesfinancieras.controller;

import com.amarilo.msobligacionesfinancieras.controller.request.PageRequestDto;
import com.amarilo.msobligacionesfinancieras.controller.request.PaymentSearchCriteria;
import com.amarilo.msobligacionesfinancieras.controller.response.PageResponseDto;
import com.amarilo.msobligacionesfinancieras.domain.dto.PaymentDto;
import com.amarilo.msobligacionesfinancieras.domain.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("api/financial-liabilities/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Permite obtener el listado de pagos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el listado de pagos",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponsePayment.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("search")
    public ResponseEntity<PageResponseDto<PaymentDto>> findAllPaymentsBySearchCriteria(@RequestBody PageRequestDto<PaymentSearchCriteria> pageRequestDto) {
        return ResponseEntity.ok(paymentService.findAllPaymentBySearchCriteria(pageRequestDto));
    }

    @Operation(summary = "Permite obtener un pago por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el pago por id",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("{id}")
    public ResponseEntity<PaymentDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @Operation(summary = "Permite guardar un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha guardado el pago exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<PaymentDto> savePayment(@Valid @RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.savePayment(paymentDto));
    }

    @Operation(summary = "Permite actualizar un pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado el pago exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Integer id,
                                                    @RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, paymentDto));
    }

    @Operation(summary = "Permite obtener el log de la integración de cuentas por cobrar por el id del pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtiene el log",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PaymentDto.class))}),
            @ApiResponse(responseCode = "204", description = "No existen registros",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Usuario no autenticado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "403", description = "Usuario sin permisos",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("integration/log/{id}")
    public ResponseEntity<String> getAccountPayableTransactionResponseLog(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getAccountPayableTransactionResponseLog(id));
    }
}

class PageResponsePayment extends PageResponseDto<PaymentDto> {
}