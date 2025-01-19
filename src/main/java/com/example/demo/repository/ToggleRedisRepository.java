package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ToggleRedisRepository implements ToggleRepository{

    private final RedisTemplate<String, String> redisTemplate;
    private final RedisConnectionFactory connectionFactory;
    private final RedisConnectionFactory redisConnectionFactory;

    private final static String MATCH_PREFIX = "toggle*";

    @Override
    public Map<String, Boolean> getToggleMap() {
        Map<String, Boolean> map = new HashMap<>();

        RedisConnection connection = redisConnectionFactory.getConnection();
        var scanOptions = ScanOptions.scanOptions()
                .match(MATCH_PREFIX)
                .count(100)
                .build();
        Cursor<byte[]> scan = connection.scan(scanOptions);
        while (scan.hasNext()) {
            String key = new String(scan.next());
            Boolean value = Boolean.valueOf(redisTemplate.opsForValue().get(key));
            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }
}
