package com.cxl.order.service;

import com.cxl.order.util.ReturnT;

public interface IDistributed {
    /**
     * 买 一  单个商品
     * @param pid 商品ID
     * @param userId 用户ID
     * @return
     */
    ReturnT RedisLock(long pid, long userId);
    /**
     * 买 一  单个商品
     * @param pid 商品ID
     * @param userId 用户ID
     * @return
     */
    ReturnT zksLock(long pid,long userId);

    /**
     * 买 二 多个商品
     * @param pid 商品ID
     * @param userId 用户ID
     * @param number 商品数量
     * @return
     */
    ReturnT startLock(long pid,long userId,long number);
}
