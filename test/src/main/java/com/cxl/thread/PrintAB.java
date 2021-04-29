package com.cxl.thread;

import java.util.concurrent.CountDownLatch;

public class PrintAB implements IPrintAB{
    private volatile boolean flag=false;


    @Override
    public void printAB() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()->{
            if (!flag){
                System.out.println("thread:"+Thread.currentThread().getName()+"------->"+"  "+"A");
                flag=true;
            }
            countDownLatch.countDown();
        },"t1").start();
        new Thread(()->{
            if (flag){
                System.out.println("thread:"+Thread.currentThread().getName()+"------->"+"  "+"B");
                flag=false;
            }
            countDownLatch.countDown();
        },"t2").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintAB printAB = new PrintAB();
        printAB.printAB();
    }
}
