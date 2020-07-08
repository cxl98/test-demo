package com.cxl.thread;

class MyNet implements Runnable{
    private Object Lock;

    public MyNet(Object lock) {
        this.Lock = lock;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(6000);
            synchronized (this.Lock){
                Lock.notify();
                System.out.println("唤醒了");
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"执行完了");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyRun implements Runnable{
    private Object lock;

    public MyRun(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.println("+++++:"+Thread.currentThread().getName());
        System.out.println("------:"+Thread.currentThread().getName());
        try {
            synchronized (this.lock){
                System.out.println("请求数据");
                new Thread(new MyNet(this.lock)).start();
                this.lock.wait();
                System.out.println("已经获取数据");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("操作完成"+Thread.currentThread().getName());
    }
}
public class SynchronousNonBlocking {
    public static void main(String[] args) {
        Object lock=new Object();
        for(int i = 0; i <3 ; i++) {
          new Thread(new MyRun(lock),String.valueOf(i)).start();
        }
    }
}
