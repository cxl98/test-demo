package com.cxl.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private AtomicInteger count;

    @Override
    public void run() {
        System.out.println("由" + MyThread.currentThread().getName() + "计算=" + count);
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "a");
        Thread thread1 = new Thread(myThread, "a1");
        Thread thread2 = new Thread(myThread, "a2");
        Thread thread3 = new Thread(myThread, "a3");
        Thread thread4 = new Thread(myThread, "a4");
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
