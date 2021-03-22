package com.cxl.escapeAnalysis;

public class EscapeAnalysis {

    /**
     * 锁消除
     */
    public static String getString(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }


    /**
     * 标量替换
     */
    public Person p;

    public void escape(){
        p=new Person(1,2);
    }

    public int noEscape(){
        Person person=new Person(1,3);
        return person.a+person.b;
    }
    static class Person{
        public int a;
        public int b;

        public Person(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    void testEscape(){
        int n=10000000;
        long l = System.currentTimeMillis();
        EscapeAnalysis escapeAnalysis=new EscapeAnalysis();
        for (int i = 0; i < n; i++) {
            escapeAnalysis.escape();
        }
        System.out.println("escape耗时：  "+ (System.currentTimeMillis() - l));
    }
    void testNoEscape(){
        int n=10000000;
        long l = System.currentTimeMillis();
        EscapeAnalysis escapeAnalysis=new EscapeAnalysis();
        for (int i = 0; i < n; i++) {
            escapeAnalysis.noEscape();
        }
        System.out.println("noEscape耗时：  "+ (System.currentTimeMillis() - l));
    }
//    public static void main(String[] args) {
//        EscapeAnalysis escapeAnalysis=new EscapeAnalysis();
//        escapeAnalysis.testEscape();
//        escapeAnalysis.testNoEscape();
//    }
    /**
     *栈上分配
     */
    static class User{
        private int id;
        private String name;
        public User(){}
    }
    private static  User user;
    public static void escapeFoo() {
        user=new User();
        user.id=1;
        user.name="aaa";
    }
    public  void noEscapeFoo() {
        user=new User();
        user.id=1;
        user.name="bbb";
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            escapeFoo();
        }
        System.out.println("耗时: "+(System.currentTimeMillis()-l));
//        escapeFoo();//逃逸
//        EscapeAnalysis escapeAnalysis=new EscapeAnalysis();
//        escapeAnalysis.noEscapeFoo();//没有逃逸
    }



}
