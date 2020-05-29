package com.cxl.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RedisLock {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);
    private static RedisClient redisClient;
    private static StatefulRedisConnection<String, String> connection;

    static {
        redisClient = RedisClient.create("redis://localhost");
        connection = redisClient.connect();
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
     * 调用set后的返回值
     */
    private static final String OK = "OK";

    /**
     * 调用get后的返回值
     */
    private static final String NIL = "nil";
    /**
     * 默认请求锁的超时时间(ms 毫秒)
     */
    private static final long TIMEOUT = 100;
    /**
     * 默认锁的有效时间(s)
     */
    private static final int EXPIRE = 100;
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

    /**
     * 锁标记
     */
    private boolean locked = false;

    public RedisLock(String lockKey) {
        this.lockKey = lockKey;
    }

    public RedisLock(String lockKey, int expireTime) {
        this.lockKey = lockKey;
        this.expireTime = expireTime;
    }

    public RedisLock(String lockKey, int expireTime, long timeout) {
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        this.timeout = timeout;
    }

    public void lock() {
        value = UUID.randomUUID().toString();
        set();
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    private String set() {
        SetArgs setArgs = new SetArgs();
        setArgs.nx();
        setArgs.ex(expireTime);
        return connection.sync().set(lockKey, value, setArgs);
    }

    public boolean tryLock() {
        value = UUID.randomUUID().toString();
        long timeout_ = timeout * 600;
        long currentTimeMillis = System.currentTimeMillis();
        while ((System.currentTimeMillis() - currentTimeMillis) < timeout_) {
            String set = set();
            if (OK.equalsIgnoreCase(set)) {
                locked = true;
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return locked;
    }

    public boolean unlock() {
        if (locked) {
            String s = connection.sync().get(lockKey);
            if (!NIL.equalsIgnoreCase(s)) {
                destroy();
                locked=false;
                return true;
            }
        }
        return locked;
    }

    public void destroy() {
        if (null != lockKey) {
            connection.sync().del(lockKey);
        }
    }

    public static void main(String[] args) {
        RedisLock redisLock = new RedisLock("xx");
        redisLock.lock();
    }
}
