package com.cxl.test;

import com.cxl.order.OrderApplication;
import com.cxl.order.dao.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MongoTest {
    @Autowired
    private UserRepository userRepository;
    @Before
    public void init(){
        userRepository.deleteAll();
    }
    @Test
    public void userTest(){

    }

}
