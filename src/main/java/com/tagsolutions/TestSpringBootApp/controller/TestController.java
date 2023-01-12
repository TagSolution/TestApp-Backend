package com.tagsolutions.TestSpringBootApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://tagsolution.github.io/TestApp-Frontend", "*"})
@RestController
@RequestMapping("/")
public class TestController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public String getTestHola() {
        return "Esta es la Homepage Wacho! ;)";
    }
}
