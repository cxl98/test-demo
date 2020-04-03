package com.cxl.hakaricp;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass(com.zaxxer.hikari.HikariDataSource.class)
public class HikariCP {

    @Autowired
    private HikariCPProperties hikariCPProperties;

    @Bean
    public DataSource HikariCpDataSource(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setJdbcUrl(hikariCPProperties.getUrl());
        hikariDataSource.setUsername(hikariCPProperties.getUsername());
        hikariDataSource.setPassword(hikariCPProperties.getPassword());
        hikariDataSource.setDriverClassName(hikariCPProperties.getDriverClassName());
        return hikariDataSource;
    }
}
