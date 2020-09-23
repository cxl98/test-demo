package com.cxl.order.controller;

import com.cxl.order.entry.Order;
import com.cxl.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "addOrder",method = RequestMethod.POST)
    @ResponseBody
    public String addOrder(Order order){
        int i = orderService.addOrder(order);
        if (1!=i){
            return "订单成功";
        }
        return "订单失败";
    }

    @RequestMapping(value = "showOrder",method = RequestMethod.POST)
    @ResponseBody
    public Order showOrder(String username){
        return orderService.showOrder(username);
    }
}
