package com.codeLife.openThirdParty.infrastructure.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作工具类，封装了常用的Redis操作方法。
 */
@Component
public class RedisUtil {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 存入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    /**
     * 存入缓存并设置过期时间
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间（秒）
     */
    public void setWithExpireTime(String key, Object value, long expireTime) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, TimeUnit.SECONDS);
    }
    /**
     * 存入缓存并设置过期时间
     * @param key 键
     * @param value 值
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     */
    public void setWithExpireTime(String key, Object value, long expireTime,TimeUnit timeUnit) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, expireTime, timeUnit);
    }

    /**
     * 根据键获取值
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     * @param key 键
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除缓存
     * @param keys 键的集合
     */
    public void deleteBatch(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 判断缓存是否存在
     * @param key 键
     * @return true-存在，false-不存在
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}