package com.cxl.order.service;

import com.cxl.order.dao.OrderDao;
import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Order;
import com.cxl.order.util.ReturnT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProducerDao producerDao;

    @Autowired
    private DistributedService distributedService;
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    


    @Transactional
    public int addOrder(Order order) throws Exception {
        LOGGER.info("开始卖");
        ReturnT returnT = distributedService.RedisLock(order.getPName(), order.getId());
        if (returnT.getCode() == ReturnT.FAIL_CODE) {
            return ReturnT.FAIL_CODE;
        }
        return ReturnT.SUCCESS_CODE;
    }

    public Order showOrder(String username) {
        if (null != username) {
            return orderDao.selectByUsername(username);
        }
        return null;
    }
}
