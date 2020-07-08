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
            System.out.println(Thread.currentThread().getName()+"  ");
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println("set");
        } finally {
            lock.unlock();
        }

    }
}

public class Test {
    public static void main(String[] args) {
        P p=new P();
        for(int i = 0; i <10; i++) {
          new Thread(p,String.valueOf(i)).start();
        }
    }
}
