package test;

public class CountDemo {
    private long time=getNowTime();
    public int repCount=0;
    public final int limit=100;
    public final long interval=6000;
    public boolean grant(){
        System.out.println("xxxxxxxxxxxx"+time);
        long now =getNowTime();
        System.out.println("aaaaaaaaa"+now);
        if (now<time+interval){
            repCount++;
            return repCount<=limit;
        }else{
            time=now;
            repCount=1;
            return true;
        }
    }
    private long getNowTime() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {
        CountDemo countDemo=new CountDemo();
        long nowTime = countDemo.getNowTime();
        System.out.println("nowtime: "+nowTime);
        for (int i = 0; i <101 ; i++) {
            boolean grant = countDemo.grant();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i+"  "+grant);
        }
        long time = countDemo.getNowTime();
        System.out.println("end "+time);
        System.out.println("start-end="+ (time-nowTime));
    }
}
