package com.cxl.test;

import com.cxl.order.OrderApplication;
import com.cxl.order.dao.ProducerDao;
import com.cxl.order.entry.Producer;
import com.cxl.order.util.RedisService;
import com.cxl.order.entry.User;
import com.cxl.order.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class TestUser {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private ProducerDao producerDao;
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

    @Test
    public void testProduct(){
        List<Producer> maps = producerDao.allRepertory();
//            maps.stream().iterator().forEachRemaining(System.out::println);
        for (Producer map : maps) {
            System.out.println(map);
        }
//        Map<Integer, Producer> stringProducerMap = producerDao.allRepertory();
//        Collection<Producer> values = stringProducerMap.values();
//        Producer producer = values.stream().parallel().findFirst().get();
//        System.out.println(producer);


    }

    @Test
    public void testByIdProduct(){
        int repertoryById = producerDao.getRepertoryById(0);
        System.out.println(repertoryById);
    }
}
