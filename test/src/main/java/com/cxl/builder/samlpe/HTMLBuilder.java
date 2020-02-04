package com.cxl.builder.samlpe;

import com.cxl.builder.samlpe.Builder;

import java.io.*;

public class HTMLBuilder extends Builder {
    private String fileName;
    private PrintWriter writer;
    public void makeTitle(String title) {
        fileName=title+".html";
        try {
            writer=new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"zh-CN\"><head><meta charset=\"UTF-8\"><title>"+title+"</title></head><body>");
        writer.println("<h1>"+title+"</h1>");
        writer.flush();
    }

    public void makeString(String str) {
        writer.println("<p>"+str+"</p>");
        writer.flush();
    }

    public void makeItem(String[] items) {
        writer.println("<u1>");
        for (String item: items) {
            writer.println("<li>"+item+"</li>");
        }
        writer.println("</u1>");

    }

    public void close() {
        writer.println("</body></html>");
        writer.flush();
        if (writer != null) {
            writer.close();
        }
    }
    public String getResult(){
        return fileName;
    }
}
