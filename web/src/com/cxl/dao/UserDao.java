package com.cxl.dao;

import com.cxl.entry.User;
import com.cxl.utils.MysqlConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private User user=null;
    public User findUserById(String id) throws SQLException, IOException, ClassNotFoundException {
         user=new User();
        connection = MysqlConnectionUtil.getInstance().getConnection();
        ps = connection.prepareStatement("SELECT * FROM t_user WHERE id=?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (null != rs && rs.next()) {
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            return user;
        }
        return user;
    }

    public User findUserByName(String name) throws SQLException, IOException, ClassNotFoundException {
        user=new User();
        connection = MysqlConnectionUtil.getInstance().getConnection();
        ps = connection.prepareStatement("SELECT * FROM t_user WHERE name=?");
        ps.setString(1, name);
        rs = ps.executeQuery();
        if (null != rs && rs.next()) {
            user.setId(rs.getString(1));
            user.setName(rs.getString(2));
            user.setPassword(rs.getString(3));
            return user;
        }
        return user;
    }

//    public int insertUser(User user) throws SQLException, IOException, ClassNotFoundException {
//        connection = MysqlConnectionUtil.getInstance().getConnection();
//        ps = connection.prepareStatement("insert into t_user VALUES (?,?,?)");
//
//        rs = ps.executeQuery();
//    }

}
