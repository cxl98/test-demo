package com.cxl.singleton;

public class Singleton1 {
    private static Singleton1 singleton=null;
    private Singleton1(){
        sleep();
    }

    public synchronized static Singleton1 getInstance() {
        if (null==singleton&&!"null".equals(singleton)) {
            singleton=new Singleton1();
        }
        return singleton;
    }
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
