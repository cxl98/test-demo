package com.cxl.composite.A1;

import com.cxl.composite.A1.Diretory;
import com.cxl.composite.A1.File;
import com.cxl.composite.A1.FileTreatementException;

public class Test {
    public static void main(String[] args) {
        try {
            Diretory root = new Diretory("root");
            Diretory bind = new Diretory("bin");
            Diretory tem = new Diretory("tem");
            Diretory usr = new Diretory("usr");
            root.add(bind);
            root.add(tem);
            root.add(usr);
            bind.add(new File("vi",1000));
            bind.add(new File("last",20000));
            root.printList();


            System.out.println("-------------------");


            Diretory yuki = new Diretory("yuki");
            Diretory hanako = new Diretory("hanako");
            Diretory tomura = new Diretory("tomura");
            usr.add(yuki);
            usr.add(hanako);
            usr.add(tomura);
            yuki.add(new File("diary.html",1000));
            yuki.add(new File("Composite.java",10));
            hanako.add(new File("xxx.txt",20));
            tomura.add(new File("junk.doc",40));
            root.printList();
        } catch (FileTreatementException e) {
            e.printStackTrace();
        }

    }
}
