package com.cxl.order.service;

import com.cxl.order.dao.UserDao;
import com.cxl.order.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public int insertUser(User user){
        if (null!=user){
            userDao.insertUser(user);
        }
        return 0;
    }

    public User selectByUsername(String username,String password){
        User user = null;
        if (null!=username){
            user=userDao.selectByUsername(username,password);
        }
        return user;
    }
}
