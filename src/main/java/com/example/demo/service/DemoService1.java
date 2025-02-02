package com.example.demo.service;

import com.example.demo.toggle.Toggle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoService1 implements DemoServicePort{

    @Override
    @Toggle(featureId = "toggle:feature2", fallbackMethod = "fallbackMethod")
    public String justPrint() {
        return "justPrint";
    }

    @Toggle(featureId = "toggle:feature3", fallbackMethod = "fallbackMethod")
    public String justPrint2() {
        return "justPrint2";
    }

    public String fallbackMethod() {
        log.info("execute fallbackMethod");
        return "fallbackMethod";
    }
}
