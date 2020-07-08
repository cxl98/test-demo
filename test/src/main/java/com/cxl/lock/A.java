package com.cxl.lock;

class Util{
    static int A;
}

public class A implements Runnable {

    @Override
    public void run() {
        xxx();
    }

    public void xxx(){
        for (int i = 0; i <1000 ; i++) {

            Util.A++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        Thread thread1=new Thread();
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(Util.A);
    }
}
