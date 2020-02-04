package com.cxl.builder.A1;

public class StringBuild extends Builder {
    private StringBuffer stringBuffer=new StringBuffer();
    protected void buildTitle(String title) {
           stringBuffer.append("+++++++++++++++++++++++\n");
           stringBuffer.append(title);
           stringBuffer.append("\n");
    }

    protected void buildString(String str) {
        stringBuffer.append("    "+str+"\n");
    }

    protected void buildItems(String[] items) {
        for (String item: items) {
            stringBuffer.append(item+"\n");
        }
    }

    protected void buildDone() {
    stringBuffer.append("++++++++++++++++++++++++");
    }
    public String getResult(){
        return stringBuffer.toString();
    }
}
