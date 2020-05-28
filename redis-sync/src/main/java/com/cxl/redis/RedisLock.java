package com.cxl.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisLock {
    private static final Logger LOGGER= LoggerFactory.getLogger(RedisLock.class);
    private static RedisClient redisClient;
    private StatefulRedisConnection<String,String> connection;
    static {
        redisClient=RedisClient.create("redis://localhost");
        LOGGER.info("redis init success");
    }
    /**
     * EX seconds:设置键的过期时间为second秒
     * PX millisecounds:设置键的过期时间为millisecounds 毫秒
     * NX:只在键不存在的时候,才对键进行设置操作
     * XX:只在键已经存在的时候,才对键进行设置操作
     * SET操作成功后,返回的是OK,失败返回NIL
     */

    /**
     *NX:只在键不存在的时候,才对键进行设置操作
     */
    private static final String NX="NX";
    /**
     *  EX seconds:设置键的过期时间为second秒
     */
    private static final String EX="EX";
    /**
     *  PX millisecounds:设置键的过期时间为millisecounds 毫秒
     */
    private static final String PX="PX";

    /**
     * 调用set后的返回值
     */
    public static final String OK = "OK";

    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long TIMEOUT = 100;
    /**
     * 默认锁的有效时间(s)
     */
    public static final int EXPIRE = 30;
    /**
     * 锁对应的key
     */
    private String lockKey;
    /**
     * 锁对应的value
     */
    private String value;


    /**
     * 锁的有效时间(s)
     */
    private int expireTime = EXPIRE;

    /**
     * 请求锁的超时时间(ms)
     */
    private long timeout = TIMEOUT;
    public void lock(){

    }



}
