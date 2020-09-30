package com.cxl.test;

import com.cxl.order.OrderApplication;
import com.cxl.order.config.RedisService;
import com.cxl.order.entry.User;
import com.cxl.order.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class TestUser {
    private static final Logger LOGGER= LoggerFactory.getLogger(TestUser.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Test
    public void testInsertUser(){
        User user=new User();
        user.setId(1);
        user.setUsername("cxl");
        user.setPassword("123456");
        userService.insertUser(user);
    }

    @Test
    public void testSelectByUser(){
        User user = userService.selectByUsername("cxl","123456");
        System.out.println(user);
    }

    @Test
    public void testRedis(){
        User user=new User();
        user.setId(1);
        user.setUsername("cxl");
        user.setPassword("123456");
        redisService.set("xx",user);
    }
    @Test
    public void testGetRedis(){
        Object xx = redisService.get("xx");
        System.out.println(xx);
    }
}
