package com.cxl.order.util;

import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisCache implements Cache {
    private final Cache delegate;

    @Autowired
    private RedisService redisService;

    public RedisCache(Cache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void putObject(Object o, Object o1) {
        redisService.set((String) o, o1);
    }

    @Override
    public Object getObject(Object o) {
        return redisService.get((String) o);
    }

    @Override
    public Object removeObject(Object o) {
        return redisService.del((String) o);
    }

    @Override
    public void clear() {
        for (int i = 0; i < delegate.getSize(); i++) {
            redisService.del(delegate.getId());
        }
    }

    @Override
    public int getSize() {
        return 0;
    }
}
