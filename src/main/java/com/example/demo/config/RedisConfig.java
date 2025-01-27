package com.example.demo.config;

import com.example.demo.service.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;


    // RedisConnectionFactory를 통해 Lettuce 연결을 설정
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 로컬 Redis 서버의 기본 설정 (호스트: localhost, 포트: 6379)
        return new LettuceConnectionFactory(redisProperties.host, redisProperties.port);
    }

    // RedisTemplate 설정
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        // 키와 값을 String으로 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(
                listenerAdapter,
                new org.springframework.data.redis.listener.ChannelTopic(redisProperties.channelFeature));
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessage"); // 메시지 처리 메서드 지정
    }

    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic(redisProperties.channelFeature);
    }
}
