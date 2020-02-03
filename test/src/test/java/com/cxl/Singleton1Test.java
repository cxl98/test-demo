package com.cxl;

import com.cxl.singleton.Singleton1;

public class Singleton1Test extends Thread {
    public static void main(String[] args) {
        new Singleton1Test("A").start();
        new Singleton1Test("b").start();
        new Singleton1Test("C").start();
    }

    @Override
    public void run() {
        Singleton1 instance = Singleton1.getInstance();
        System.out.println(getName()+":  "+instance);
    }

    public Singleton1Test(String name) {
        super(name);
    }

}
