package com.cxl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.fastjson.JSON;
import com.cxl.druid.DruidDataSourceProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConnectTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DruidDataSourceProperty druidDataSourceProperty;

    @Test
    public void testDataSource() {
        long start = System.currentTimeMillis();
//        new Thread(()->{
            DataSource dataSource=applicationContext.getBean(DataSource.class);
            System.out.println(dataSource);
            System.out.println(dataSource.getClass().getName());
            System.out.println(JSON.toJSONString(druidDataSourceProperty));
//        }).start();
        long end = System.currentTimeMillis();
        System.out.println("执行了"+(end-start)+"秒");
    }

    @Test
    public void testData() throws IOException, SQLException {
        Properties properties=new Properties();
        InputStream inputStream=new FileInputStream("/home/cxl/cxl/test-demo/druid/src/main/resources/application.properties");
        properties.load(inputStream);
        String driverClassName = properties.getProperty("spring.datasource.type");
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        long l = System.currentTimeMillis();
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection.getClass().getName());
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l+"秒");
    }
}
