package com.cxl.order.controller;

import com.cxl.order.entry.User;
import com.cxl.order.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    @ResponseBody
    public int registry(User user) {
        if (null != user.getUsername() && null != user.getPassword()) {
            return userService.insertUser(user);
        }
        return 0;
    }
}
