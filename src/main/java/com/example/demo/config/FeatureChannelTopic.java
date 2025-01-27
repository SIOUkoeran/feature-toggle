package com.example.demo.config;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class FeatureChannelTopic extends ChannelTopic {

    public FeatureChannelTopic() {
        super("feature");
    }
}
