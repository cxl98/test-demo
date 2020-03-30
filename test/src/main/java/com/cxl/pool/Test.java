package com.cxl.pool;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        ConnectionImpl connection=new ConnectionImpl(10);
            long start = System.currentTimeMillis();
        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                try {
                    connection.getConnection();
                    System.out.println("xxxxxxxx"+connection.getConnection());
                    connection.releaseConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
        }

            long end = System.currentTimeMillis();
            long sum=end-start;
            System.out.println(sum);

    }

}
