package com.cxl.builder.samlpe;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.next();
        if (s.equals("plain")){
            TextBuilder textBuilder=new TextBuilder();
            Director director=new Director(textBuilder);
            director.construct();
            String result=textBuilder.getResult();
            System.out.println(result);
        }else if (s.equals("html")){
            HTMLBuilder htmlBuilder=new HTMLBuilder();
            Director director=new Director(htmlBuilder);
            director.construct();
            String result=htmlBuilder.getResult();
            System.out.println(result);
        }else{
            System.exit(0);
        }
    }

}
