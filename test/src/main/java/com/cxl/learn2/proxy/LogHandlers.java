package com.cxl.learn2.proxy;

import com.cxl.learn2.staticproxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogHandlers implements InvocationHandler {
     Student student;

    public LogHandlers(Student student) {
        this.student = student;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(student,args);
    }
}
