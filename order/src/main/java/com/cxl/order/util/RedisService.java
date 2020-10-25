package com.cxl.order.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

public interface RedisService {
     boolean set(String key, Object value, long time, TimeUnit timeout);
    /**
     *redis set
     * @param key key
     * @param value value
     * @param timeout 过期时间
     */
    void set(String key,Object value,long timeout);

    /**
     * redis set
     * @param key
     * @param value
     */
    void set(String key,Object value);

    /**
     *
     * @param key "aa"
     * @return object
     */
    Object get(String key);

    Object get(String key,String hashKey);
    /**
     *
     * @param key
     * @return
     */
    Boolean del(String key);

    /**
     * 批量删除
     * @param keys
     * @return
     */
    Long del(List<String> keys);

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @return
     */
    Boolean expire(String key,long timeout);

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    Boolean hasKey(String key);

    /**
     *　按da 自增
     * @param key
     * @param da
     * @return
     */
    Long incr(String key,long da);

    Long decr(String key, String hashKey, long delta);

}
