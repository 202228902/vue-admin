package com.galaxy.empvue.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void deleteByPrefix(String prefix) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            Set<byte[]> keys = connection.keys((prefix + "*").getBytes());
            assert keys != null;
            if (!keys.isEmpty()) {
                connection.del(keys.toArray(new byte[0][]));
            }
            return null;
        });
    }
}
