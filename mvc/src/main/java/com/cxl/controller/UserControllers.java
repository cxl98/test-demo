package com.cxl.controller;

import com.cxl.entry.User;
import com.cxl.service.UserServerImpls;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserControllers {
    @Autowired
    private UserServerImpls userServerImpls;

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String login(User user) {
        user = userServerImpls.findUserByNameAndPassword(user.getUsername(), DigestUtils.md5Hex(user.getPassword()));

        if (null == user || null == user.getUsername()) {
            return "用户不存在，用户名密码错误";
        }
        return "hello " + user.getUsername();
    }

    @RequestMapping(value = "registry", method = RequestMethod.POST)
    @ResponseBody
    public String registry(User user) {
        boolean registry = userServerImpls.registry(user);
        if (registry) {
            return "hello " + user.getUsername();
        }
        return "注册失败";
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public String test(String username, String password) {
        return username + password;
    }
}
