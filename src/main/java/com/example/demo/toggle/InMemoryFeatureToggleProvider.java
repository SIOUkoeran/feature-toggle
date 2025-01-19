package com.example.demo.toggle;

import org.springframework.stereotype.Component;

@Component
public class InMemoryFeatureToggleProvider implements FeatureToggleProvider {

    @Override
    public boolean isFeatureEnabled(String feature) {
        //TODO 입력된 feature 가 활성 여부 확인하여 반환하는 로직 구현 #1
        return false;
    }
}
