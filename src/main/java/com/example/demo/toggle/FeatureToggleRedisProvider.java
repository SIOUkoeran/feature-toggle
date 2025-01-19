package com.example.demo.toggle;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FeatureToggleRedisProvider implements FeatureToggleProvider{

    private final Map<String, Boolean> featureTogglesMap;

    public FeatureToggleRedisProvider() {
        this.featureTogglesMap = Map.of();
    }

    @Override
    public boolean isFeatureEnabled(String feature) {
        //TODO 해당 기능 상태를 판별하는 로직 구현 필요 #1
        return false;
    }
}
