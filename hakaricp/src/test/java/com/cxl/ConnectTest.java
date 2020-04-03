package com.cxl;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectTest {
    @Test
    public void connectDataSource() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("/home/cxl/cxl/test-demo/hakaricp/src/main/resources/db.properties");
        properties.load(inputStream);
        String driverClassName = properties.getProperty("spring.datasource.type");
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(driverClassName);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        long l = System.currentTimeMillis();
        Connection connection = hikariDataSource.getConnection();
        System.out.println(connection.getClass().getName());
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l+"秒");
    }
}
