package com.example.demo.toggle;

import com.example.demo.model.Feature;

import java.util.List;


public class InMemoryFeatureToggleProvider implements FeatureToggleProvider {

    @Override
    public boolean isFeatureEnabled(String feature) {
        //TODO 입력된 feature 가 활성 여부 확인하여 반환하는 로직 구현 #1
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateFeatureEnabled(String feature, boolean enabled) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Feature.Toggle> getAllFeature() {
        //TODO
        throw new UnsupportedOperationException();
    }
}
