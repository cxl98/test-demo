package com.cxl.learn2.staticproxy;

public class StudentsProxy implements Person {
    Student student;

    public StudentsProxy(Person student) {
        if (student.getClass() == Student.class) {
            this.student = (Student) student;
        }
    }

    public void giveTask() {
        student.giveTask();
    }
}
