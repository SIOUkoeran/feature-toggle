package com.example.demo.service;

import com.example.demo.model.Feature;
import com.example.demo.model.FeatureMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeatureToggleService {

    /**
     * 레디스에 변경정보를 저장하고,
     * 채널에 이를 알린다.
     * @return 성공여부
     */
    boolean publishChangedToggleStatus(FeatureMessageDto featureMessageDto);

    /**
     * 레디스 채널을 sub 하여, 수정정보를 전달 받는다.
     * @return 변경 정보 dto
     */
    FeatureMessageDto subscribeChangedToggleStatus(String message, String channel);

    List<Feature.Toggle> getAllFeature();
}
