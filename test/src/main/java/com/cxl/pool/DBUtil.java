package com.cxl.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private static String jdbcDriver;
    private static String jdbcUrl;
    private static String userName;
    private static String password;

    public static Connection getConnection() throws SQLException {
        File config=new File("/home/cxl/cxl/test-demo/test/src/main/resources/mysql.properties");
        Properties properties=new Properties();

        try {
            FileInputStream fileInputStream=new FileInputStream(config);
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jdbcDriver=properties.getProperty("DRIVER");
        jdbcUrl=properties.getProperty("URL");
        userName=properties.getProperty("USERNAME");
        password=properties.getProperty("PASSWORD");
        Connection connection=null;
        try {
            Class.forName(jdbcDriver);
            connection= DriverManager.getConnection(jdbcUrl,userName,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
