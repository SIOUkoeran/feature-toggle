package com.example.demo.service;

import com.example.demo.model.FeatureMessageDto;
import com.example.demo.toggle.FeatureToggleProvider;
import com.example.demo.toggle.FeatureToggleRedisProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeatureToggleRedisService implements FeatureToggleService {

    private final RedisPublisher redisPublisher;
    private final FeatureToggleProvider featureToggleProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;


    @Override
    public boolean publishChangedToggleStatus(FeatureMessageDto featureMessageDto) {
        String rawMessage = writetoString(featureMessageDto);
        try {
            redisTemplate.opsForValue().set(featureMessageDto.featureId(), featureMessageDto.message());
            redisPublisher.publish(rawMessage);
        }catch (Exception e){
            log.error("FeatureToggleRedisService publishChangedToggleStatus error", e);
            return false;
        }
        return true;
    }

    protected <T> String writetoString(T any) {
        try {
            return objectMapper.writeValueAsString(any);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FeatureMessageDto subscribeChangedToggleStatus(String message, String channel) {
        log.info("Subscribed to change toggle status : message={} channel={}", message, channel);
        FeatureMessageDto featureMessageDto = convert(message, FeatureMessageDto.class);
        Boolean isEnabled = convert(featureMessageDto.message(), Boolean.class);
        featureToggleProvider.updateFeatureEnabled(featureMessageDto.featureId(), isEnabled);
        return featureMessageDto;
    }

    protected <T> T convert(String raw, Class<T> clazz) {
        try {
            return objectMapper.readValue(raw, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
