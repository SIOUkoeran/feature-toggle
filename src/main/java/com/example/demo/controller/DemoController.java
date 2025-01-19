package com.example.demo.controller;

import com.example.demo.service.DemoServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {


    private final DemoServicePort demoServicePort;

    @GetMapping("/")
    protected  String demoRouter() {
        String s = demoServicePort.justPrint();
        return s;
    }
}
