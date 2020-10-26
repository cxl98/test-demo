package com.cxl.order.service;

import com.cxl.order.dao.UserDao;
import com.cxl.order.entry.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean insertUser(User user){
        User user1 = userDao.selectByUsername(user.getUsername());
        if (null!=user1&&user1.getUsername()!=null&&user1.getUsername().equals(user.getUsername())){
            return true;
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return userDao.insertUser(user)!=0;
    }
    @Cacheable(cacheNames = "user",key = "#username")
    public User selectByUsername(String username,String password){
        User user = null;
        if (null!=username){
            user=userDao.selectByUsernameAndPassword(username,password);
        }
        return user;
    }
}
