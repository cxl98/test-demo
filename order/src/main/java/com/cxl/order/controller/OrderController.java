package com.cxl.order.controller;

import com.cxl.order.annotation.Log;
import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Order;
import com.cxl.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    private final static Logger LOGGER= LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "addOrder",method = RequestMethod.POST)
    @ResponseBody
    public String addOrder(@RequestBody Order order) throws Exception {
        int i = orderService.addOrder(order);
        if (1!=i){
            return "订单成功";
        }
        return "订单失败";
    }

    @RequestMapping(value = "showOrder",method = RequestMethod.POST)
    @ResponseBody
    @Log(operation = "showOrder",type = "dev")
    public Order showOrder(String username){
        return orderService.showOrder(username);
    }
}
