package com.cxl.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  正在写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "  写入完成：");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "  正在读取：");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "  读取的结果" + result);
        } finally {
            lock.readLock().unlock();
        }

    }

    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReadWriteLock();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                readWriteLock.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                readWriteLock.get(temp + "");
            }, String.valueOf(i)).start();
        }
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
