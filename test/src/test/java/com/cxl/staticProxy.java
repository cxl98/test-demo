package com.cxl;

import com.cxl.learn2.staticproxy.Person;
import com.cxl.learn2.staticproxy.Student;
import com.cxl.learn2.staticproxy.StudentsProxy;

public class staticProxy {
    public static void main(String[] args) {
        Person student=new Student("晨曦");
        Person monitor=new StudentsProxy(student);
        monitor.giveTask();
    }


}
