package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisSubscriber {

    public void onMessage(String message, String channel) {
        log.info("Receive message: {}", message);
    }
}
