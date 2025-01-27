package com.example.demo.toggle;

import com.example.demo.repository.ToggleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Component
@Repository
public class FeatureToggleRedisProvider implements FeatureToggleProvider{

    private Map<String, Boolean> featureTogglesMap;


    public FeatureToggleRedisProvider(ToggleRepository toggleRepository) {
        featureTogglesMap = toggleRepository.getToggleMap();
    }

    
    @Override
    public boolean isFeatureEnabled(String feature) {
        return featureTogglesMap.get(feature) != null && featureTogglesMap.get(feature);
    }

    @Override
    public void updateFeatureEnabled(String feature, boolean enabled) {
        featureTogglesMap.put(feature, enabled);
    }
}
