package com.cxl.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionImpl implements ConnectionPool {
    /**
     * 空闲连接池
     */
    private LinkedBlockingDeque<Connection> idleConnectionPool;

    /**
     * 活跃连接池
     */
    private LinkedBlockingDeque<Connection> busyConnectionPool;

    /**
     * 正在使用的连接数
     */
    private AtomicInteger activeSize = new AtomicInteger(0);

    /**
     * 最大连接数
     */
    private final int maxSize;

    public ConnectionImpl(int maxSize) {
        this.maxSize = maxSize;
//        init();
        init(maxSize);
    }

    public void init() {
        idleConnectionPool = new LinkedBlockingDeque<>();
        busyConnectionPool = new LinkedBlockingDeque<>();
    }

    public void init(int maxSize) {
        idleConnectionPool = new LinkedBlockingDeque<>(maxSize);
        busyConnectionPool = new LinkedBlockingDeque<>(maxSize);
    }

    public Connection getConnection() throws SQLException {
        //从空闲池种取出一个连接
        Connection connection = idleConnectionPool.poll();
        if (null != connection) {
            //如果有连接，放到ｂｕｓｙ中;
            busyConnectionPool.offer(connection);
            return connection;
        }

        if (maxSize >= activeSize.incrementAndGet()) {// 解决maxSize>activeSize.get()的线程安全问题
            connection = DBUtil.getConnection();
            System.out.println("获取连接成功");
            busyConnectionPool.offer(connection);
            return connection;
        }

        //如果空闲连接池数达到maxSize,则阻塞等待归回

        System.out.println("排队等待连接归回");
        try {
            connection = idleConnectionPool.poll(10000, TimeUnit.MILLISECONDS);//阻塞获取连接，如果１０内有其他连接释放
            if (null == connection) {
                System.out.println("等待超时");
                throw new RuntimeException("等待连接超时");
            }
            System.out.println("等待到了一个连接");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (null != connection) {
            busyConnectionPool.remove(connection);
            idleConnectionPool.offer(connection);
        }
    }


    public void destroy() {
        if (busyConnectionPool.isEmpty() && idleConnectionPool.isEmpty()) {
            busyConnectionPool.clear();
            idleConnectionPool.clear();
        }
    }
}
