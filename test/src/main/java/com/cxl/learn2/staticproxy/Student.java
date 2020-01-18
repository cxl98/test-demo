package com.cxl.learn2.staticproxy;

public class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void giveTask() {
        System.out.println(name+"交作业了");
    }
}
