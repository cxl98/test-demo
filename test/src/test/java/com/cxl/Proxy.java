package com.cxl;

import com.cxl.learn2.proxy.ProxyFactory;
import com.cxl.learn2.staticproxy.Person;
import com.cxl.learn2.staticproxy.Student;

public class Proxy {
    public static void main(String[] args) {
        Person person=new Student("晨曦");
        Person proxy= ProxyFactory.getProxy(person);
        proxy.giveTask();
    }
}
