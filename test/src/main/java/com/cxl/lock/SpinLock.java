package com.cxl.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> atomicReference=new AtomicReference<>();
    public void lock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"   lock()");
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void unlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"   unlock()");
    }
    public static void main(String[] args) {
        SpinLock spinLock=new SpinLock();
        new Thread(()->{
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unlock();
        },"A").start();

        new Thread(()->{
            spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unlock();
        },"B").start();
    }
}
