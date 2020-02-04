package com.cxl.builder.samlpe;

import com.cxl.builder.samlpe.Builder;

public class TextBuilder extends Builder {
    private StringBuffer buffer=new StringBuffer();
    public void makeTitle(String title) {
        buffer.append("=================");
        buffer.append("["+title+"]\n");
        buffer.append("\n");
    }

    public void makeString(String str) {
        buffer.append(' '+str+"\n");
        buffer.append("\n");
    }

    public void makeItem(String[] items) {
        for (String item: items) {
            buffer.append("   "+item+"\n");
        }
        buffer.append("\n");
    }

    public void close() {
        buffer.append("==================");
    }
    public String getResult(){
        return buffer.toString();
    }
}
