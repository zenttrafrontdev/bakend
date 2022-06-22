package com.amarilo.msobligacionesfinancieras.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/financial-liabilities/v1/financial-third-party")
public class FinanceThirdController {

    @GetMapping
    public String holaMundo(){
        return "Hola Mundo";
    }
}
