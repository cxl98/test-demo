package com.cxl.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintABAtomic implements IPrintAB{
    private static final int MAX_PRINT_NUM=100;
    private static final AtomicInteger at=new AtomicInteger(0);

    @Override
    public void printAB() {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        new Thread(()->{
            while(MAX_PRINT_NUM>at.get()){
                if (0==at.get()%2){
                    System.out.println("Thread:"+Thread.currentThread().getName()+"-------"+"num:"+at.get());
                    at.incrementAndGet();
                }
            }
            countDownLatch.countDown();
        },"t1").start();
        new Thread(()->{
            while(MAX_PRINT_NUM>at.get()){
                if (1==at.get()%2){
                    System.out.println("Thread:"+Thread.currentThread().getName()+"-------"+"num:"+at.get());
                    at.incrementAndGet();
                }
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
        PrintABAtomic printABAtomic=new PrintABAtomic();
        printABAtomic.printAB();
    }
}
