package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoService1Test {

    @Autowired
    DemoServicePort demoServicePort;

    @Test
    void testDemoService1WithToggleFeature() {
        String s = demoServicePort.justPrint();
    }
}