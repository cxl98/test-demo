package com.cxl.dao;

import com.cxl.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findUserById(String id){
        final User user=new User();
        String sql="select * from user where id=?";
        jdbcTemplate.query(sql,new Object[]{id}, resultSet -> {
            user.setId(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
        });
        return user;
    }

    public User findByName(String name) {
        final User user = new User();

        String sql = "SELECT * FROM user WHERE username=?";
        jdbcTemplate.query(sql, new Object[]{name}, resultSet -> {
            user.setId(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
        });
        return user;
    }

    public User findByNameAndPassword(String name, String password) {


        final User user = new User();

        String sql = "SELECT * FROM user WHERE useranme=? AND password=?";
        jdbcTemplate.query(sql, new Object[]{name, password}, resultSet -> {
            user.setId(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
        });
        return user;
    }

    public int insertUser(User user) {
        String sql = "INSERT INTO user (id,username,password) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPassword());
    }
}
