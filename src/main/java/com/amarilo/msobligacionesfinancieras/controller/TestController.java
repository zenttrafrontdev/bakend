package com.amarilo.msobligacionesfinancieras.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test/v1")
public class TestController {

    @GetMapping
    public String holaMundo(){
        return "Hola Mundo";
    }
}
