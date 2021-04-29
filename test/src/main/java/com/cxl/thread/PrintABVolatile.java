package com.cxl.thread;

import java.util.concurrent.CountDownLatch;

public class PrintABVolatile implements IPrintAB{
    private static final int MAX_PRINT_NUM=100;
    private volatile int count=0;
    @Override
    public void printAB() {
        CountDownLatch countDownLatch=new CountDownLatch(2);
        new Thread(()->{
            while(MAX_PRINT_NUM>count){
                if (count%2==0){
                    System.out.println("thread:"+Thread.currentThread().getName()+"-----"+"num:"+count);
                    count++;
                }
            }
            countDownLatch.countDown();
        },"t1").start();
        new Thread(()->{
            while(MAX_PRINT_NUM>count){
                if (count%2==1){
                    System.out.println("thread:"+Thread.currentThread().getName()+"-----"+"num:"+count);
                    count++;
                }
            }
            countDownLatch.countDown();
        },"t1").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PrintABVolatile printABVolatile=new PrintABVolatile();
        printABVolatile.printAB();
    }
}
