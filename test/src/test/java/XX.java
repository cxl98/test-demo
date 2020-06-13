import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class XX {

    static class ThreadA implements Runnable {
        private ThreadB b;
        private ThreadC c;
        private volatile int a;
        private AtomicBoolean flag = new AtomicBoolean();

        public ThreadA(ThreadB b, ThreadC c) {
            this.b = b;
            this.c = c;
            flag.set(true);
        }

        @Override
        public void run() {
            int count = 0;
            while (flag.get()) {
                Random random = new Random();
                a = random.nextInt(100);
                System.out.println("A:" + a);
                if (a % 2 == 0) {
                    c.setC(a);
                    c.run();
                } else {
                    b.setB(a);
                    b.run();
                }
                count++;
                if (count > 20) {
                    flag.set(false);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        private volatile int b;

        public void setB(int b) {
            this.b = b;
        }

        @Override
        public void run() {
            System.out.println("B:" + b);
        }
    }

    static class ThreadC implements Runnable {
        private volatile int c;

        public void setC(int c) {
            this.c = c;
        }

        @Override
        public void run() {
            System.out.println("c" + c);
        }
    }

    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        ThreadC threadC=new ThreadC();
        ThreadA threadA=new ThreadA(threadB,threadC);
        Thread thread = new Thread(threadA);
        thread.start();
    }

}
