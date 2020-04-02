package com.cxl.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(com.alibaba.druid.pool.DruidDataSource.class)
//@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
public class DruidDataSourceConfig {

    @Autowired
    private DruidDataSourceProperty druidDataSourceProperty;

    @Bean
    @Primary
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl(druidDataSourceProperty.getUrl());
        dataSource.setUsername(druidDataSourceProperty.getUsername());
        dataSource.setPassword(druidDataSourceProperty.getPassword());
        dataSource.setDriverClassName(druidDataSourceProperty.getDriverClassName());
        return dataSource;
    }
}
