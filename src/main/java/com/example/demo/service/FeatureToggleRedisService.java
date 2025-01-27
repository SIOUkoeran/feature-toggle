package com.example.demo.service;

import com.example.demo.model.FeatureMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
public class FeatureToggleRedisService implements FeatureToggleService {

    private final RedisPublisher redisPublisher;
    private final RedisSubscriber redisSubscriber;


    @Override
    public boolean publishChangedToggleStatus() {
        //TODO 레디스에 변경사항을 저장하고, channel 에 해당 변경사항을 pub 한다. #1
        return false;
    }

    @Override
    public FeatureMessageDto subscribeChangedToggleStatus() {
        //TODO 레디스 채널을 sub 하면서 변경사항을 전달받는다. #1
        return null;
    }
}
