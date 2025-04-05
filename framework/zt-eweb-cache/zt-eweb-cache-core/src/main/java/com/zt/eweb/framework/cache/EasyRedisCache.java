package com.zt.eweb.framework.cache;

import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

public class EasyRedisCache implements IEasyCache {

    RedisTemplate<String, Object> cacheRedisTemplate;

    public EasyRedisCache(RedisTemplate<String, Object> cacheRedisTemplate) {
        this.cacheRedisTemplate = cacheRedisTemplate;
    }

    @Override
    public void put(String key, Object value) {
        cacheRedisTemplate.opsForValue().set(key, value, 1, TimeUnit.DAYS);
    }

    @Override
    public void put(String key, Object value, long timeout) {
        cacheRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        cacheRedisTemplate.delete(key);
    }

    @Override
    public <T> T get(String key) {
        return (T) cacheRedisTemplate.opsForValue().get(key);
    }

}
