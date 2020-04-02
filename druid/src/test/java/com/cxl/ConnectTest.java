package com.cxl;

import com.alibaba.fastjson.JSON;
import com.cxl.druid.DruidDataSourceProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DruidDataSourceProperty druidDataSourceProperty;

    @Test
    public void testDataSource() {
        DataSource dataSource=applicationContext.getBean(DataSource.class);
        long start = System.currentTimeMillis();
        System.out.println(dataSource);
        System.out.println(dataSource.getClass().getName());
        System.out.println(JSON.toJSONString(druidDataSourceProperty));
        long end = System.currentTimeMillis();
        System.out.println("执行了"+(end-start)+"秒");
    }
}
