package com.cxl.order.interceptors;

import com.cxl.order.entry.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token=request.getHeader("token");
        if (request.getRequestURI().contains("login")||request.getRequestURI().contains("registry")){
            return true;
        }
        return false;
    }
}
