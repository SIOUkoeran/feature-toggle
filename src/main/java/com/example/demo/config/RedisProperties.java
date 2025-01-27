package com.example.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisProperties {

    @Value("${config.redis.port:6379}")
    protected Integer port;

    @Value("${config.redis.host:localhost}")
    protected String host;

    @Value("${config.redis.channel.feature:feature}")
    protected String channelFeature;

}
