package com.cxl.pool;

import java.sql.Connection;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ConnectionImpl connection=new ConnectionImpl(10);
            long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Connection connection1 = connection.getConnection();
//                    connection.releaseConnection(connection1);
                    connection.releaseConnection(connection1);
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
