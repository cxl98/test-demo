package com.cxl.composite.A2;

public class Test {
    public static void main(String[] args) {
        Directory root=new Directory("root");
        Directory usr=new Directory("usr");
        Directory cxl=new Directory("cxl");
        File file=new File("Test.java",1000);
        File file1=new File("Test1.java",2000);
        File file2=new File("cxl.java",3000);
        root.add(usr);
        root.add(file);
        usr.add(file1);
        usr.add(cxl);
        cxl.add(file2);
        root.printList();

        System.out.println("================");
//        System.out.println("cxl  "+cxl.getFullName());
//        System.out.println("file2  "+file2.getFullName());
        System.out.println(file1.findFullName("Test1.java"));
//        usr.printList("root");

    }
}
