package com.cxl.pool;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    /**
     * 初始化连接池
     */
    void init();

    /**
     * 初始化连接池
     * @param maxSize 最大连接数
     */
    void init(int maxSize);
    /**
     * 获取到一个连接
     */
    Connection getConnection() throws SQLException;

    /**
     * 释放掉连接
     */
    void releaseConnection(Connection connection);
    /**
     * 销毁连接池
     */
    void destroy();
}
