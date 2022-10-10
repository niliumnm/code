package com.stelpolvo.wiki.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * true：不存在，放一个KEY
     * false：已存在
     * @param key
     * @param second
     * @return
     */
    public boolean validateRepeat(String key, long second) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            return false;
        } else {
            redisTemplate.opsForValue().set(key, key, second, TimeUnit.SECONDS);
            return true;
        }
    }
}