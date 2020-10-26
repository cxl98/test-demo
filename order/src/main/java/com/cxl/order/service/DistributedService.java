package com.cxl.order.service;

import com.cxl.order.dao.OrderDao;
import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Order;
import com.cxl.order.entry.Producer;
import com.cxl.order.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DistributedService implements IDistributed{
    @Autowired
    private ProducerDao producerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private RedisLockUtil redisLockUtil;
    @Autowired
    private RedisService redisService;
    @Autowired
    private Id id;

    @Override
    @Transactional
    public ReturnT RedisLock(long pid, long userId) {
        boolean res=false;
        int repertoryById;
        try {
            res = redisLockUtil.tryLock("key"+pid , TimeUnit.SECONDS, 3, 20);
             repertoryById = (int) redisService.get("key" + pid);
             if (repertoryById<0){
                 repertoryById = producerDao.getRepertoryById(pid);
                 redisService.set("key" + pid,repertoryById);
             }
            if (repertoryById>0) {
                Order order=new Order();
                order.setId(id.getNextId());
                order.setUsername(String.valueOf(userId));
                order.setStatus(0);
                order.setCreateTime(new Date());
                orderDao.insetOrder(order);
                redisService.incr("key" + pid,-1);
                producerDao.updateRepertory(repertoryById-1);
            }else {
                return ReturnT.FAIL;
            }
        } finally {
            if (res){
                redisLockUtil.unlock("key"+pid);
            }
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT zksLock(long pid, long userId) {
        return null;
    }

    @Override
    public ReturnT startLock(long pid, long userId, long number) {
        return null;
    }
}
