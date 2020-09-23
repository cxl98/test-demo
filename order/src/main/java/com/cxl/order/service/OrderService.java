package com.cxl.order.service;

import com.cxl.order.dao.OrderDao;
import com.cxl.order.entry.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public int addOrder(Order order){
        if (null!=order){
            return orderDao.insetOrder(order);
        }
        return 0;
    }
    public Order showOrder(String username){
        if (null!=username){
            return orderDao.selectByUsername(username);
        }
        return null;
    }
}
