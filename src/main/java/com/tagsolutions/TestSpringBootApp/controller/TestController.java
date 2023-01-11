package com.tagsolutions.TestSpringBootApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/hola")
    public String getTestHola() {
        return "Hola desde EC2!!!";
    }
}
