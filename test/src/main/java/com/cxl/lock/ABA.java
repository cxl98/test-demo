package com.cxl.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ABA {
    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference=new AtomicReference<>();
        atomicReference.set(100);
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
       new Thread(()->{
           try {
               TimeUnit.SECONDS.sleep(4);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println(atomicReference.compareAndSet(100,2020)+"\t"+atomicReference.get());
       },"t2").start();
    }
}
