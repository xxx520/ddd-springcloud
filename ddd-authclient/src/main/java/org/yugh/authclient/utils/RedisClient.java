package org.yugh.authclient.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author: YuGenHai
 * @name: RedisClient.java
 * @creation: 2018年5月31日 下午5:50:51
 * @notes: Spring Boot Redis
 */
@Slf4j
@Component
public class RedisClient {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    protected RedisClient(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    static {
        log.info("=============> redis run ............");
    }


    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("redis set error ", e);
        }
        return result;
    }


    /**
     * 写入缓存设置时效时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("redis set time error ", e);
        }
        return result;
    }


    /**
     * 分布式锁
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean setNx(final String key, String value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.setIfAbsent(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("redis set nx error ", e);
        }
        return result;
    }


    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }


    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }


    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }


    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }


    /**
     * 哈希 添加
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }


    /**
     * 哈希获取数据
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }


    /**
     * 列表添加
     *
     * @param k
     * @param v
     */
    public void lPush(String k, Object v) {
        ListOperations list = redisTemplate.opsForList();
        list.rightPush(k, v);
    }


    /**
     * 列表获取
     *
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations list = redisTemplate.opsForList();
        return list.range(k, l, l1);
    }


    /**
     * 集合添加
     *
     * @param key
     * @param value
     */
    public void add(String key, Object value) {
        SetOperations set = redisTemplate.opsForSet();
        set.add(key, value);
    }


    /**
     * 集合获取
     *
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key) {
        SetOperations set = redisTemplate.opsForSet();
        return set.members(key);
    }


    /**
     * 有序集合添加
     *
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }


    /**
     * 有序集合获取
     *
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

}