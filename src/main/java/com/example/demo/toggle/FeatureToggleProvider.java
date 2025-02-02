package com.example.demo.toggle;

import com.example.demo.model.Feature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FeatureToggleProvider {

    /**
     * 해당 기능이 동작 가능한 상태인지 확인하는 메서드
     * @param feature 메서드 이름
     * @return 메서드 활성 여부
     */
    boolean isFeatureEnabled(String feature);

    void updateFeatureEnabled(String feature, boolean enabled);

    List<Feature.Toggle> getAllFeature();
}
