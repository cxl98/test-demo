package com.cxl.learn2.proxy;

import com.cxl.learn2.staticproxy.Person;
import com.cxl.learn2.staticproxy.Student;

import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Person getProxy(Person target){
        return (Person) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new LogHandlers((Student) target));
    }
}
