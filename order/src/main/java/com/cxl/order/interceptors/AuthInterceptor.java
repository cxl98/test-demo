package com.cxl.order.interceptors;

import com.cxl.order.entry.User;
import com.cxl.order.service.UserService;
import com.cxl.order.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if (request.getRequestURI().contains("login") || request.getRequestURI().contains("registry")) {
            return true;
        }
        if (null != token) {
            User login = JWTUtil.unSign(token, User.class);

            if (null != login) {
                User loginResult = userService.selectByUsername(login.getUsername(), login.getPassword());
                if (null != loginResult) {
                    return true;
                } else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            } else {
                responseMessage(response, response.getWriter());
                return false;
            }
        } else {
            responseMessage(response, response.getWriter());
            return false;
        }

    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        out.print("验证未通过");
        out.flush();
        out.close();
    }
}
