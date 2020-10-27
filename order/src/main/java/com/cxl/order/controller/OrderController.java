package com.cxl.order.controller;

import com.cxl.order.annotation.Log;
import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Order;
import com.cxl.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "订单模块")
public class OrderController {
    private final static Logger LOGGER= LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @ApiOperation(value = "添加订单")
    @ApiImplicitParam()
    @PostMapping(value = "addOrder")
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
