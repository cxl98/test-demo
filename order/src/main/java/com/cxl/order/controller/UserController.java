package com.cxl.order.controller;

import com.cxl.order.annotation.Log;
import com.cxl.order.entry.User;
import com.cxl.order.service.UserService;
import com.cxl.order.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    @ResponseBody
    public boolean registry(User user) {
        if (null != user.getUsername() && null != user.getPassword()) {
            return userService.insertUser(user);
        }
        return false;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username,String password) {
        User user = userService.selectByUsername(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        if (null==user||null==user.getUsername()){
            return "用户不存在或用户名、密码错误";
        }
        String sign = JWTUtil.sign(user, 60L * 1000L * 30L);

        return "hello"+user.getUsername()+"token"+sign;
    }
}
