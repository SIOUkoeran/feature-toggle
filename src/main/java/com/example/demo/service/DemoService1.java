package com.example.demo.service;

import com.example.demo.toggle.Toggle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoService1 implements DemoServicePort{

    @Override
    @Toggle(value = "toggle:feature2", fallbackMethod = "fallbackMethod")
    public String justPrint() {
        return "justPrint";
    }

    public String fallbackMethod() {
        log.info("execute fallbackMethod");
        return "fallbackMethod";
    }
}
