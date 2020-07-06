package com.cxl.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//可重入锁
class P implements Runnable {
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println("get");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println("set");
        } finally {
        }

    }
}

public class Test {
    public static void main(String[] args) {
        P p=new P();
        new Thread(p,"xxx").start();
        new Thread(p,"aaa").start();
    }
}
