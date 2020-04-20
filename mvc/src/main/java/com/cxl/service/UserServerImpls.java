package com.cxl.service;

import com.cxl.dao.UserDao;
import com.cxl.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServerImpls  {
    @Autowired
    private UserDao userDao;



    public User findUserByName(String username) {
        return userDao.findByName(username);
    }


    public User findUserByNameAndPassword(String username, String password) {


        return userDao.findByNameAndPassword(username,password);
    }


    public boolean registry(User user) {
        if (null!=findUserByName(user.getUsername())){
            return true;
        }
        if (null==user.getId()||"".equals(user.getId())){
            user.setId(UUID.randomUUID().toString());
        }
        return userDao.insertUser(user)!=0;
    }
}
