package com.cxl.abstractfactory.listfactory;

import com.cxl.abstractfactory.factory.Item;
import com.cxl.abstractfactory.factory.Tray;

public class ListTray extends Tray {
     ListTray(String caption) {
        super(caption);
    }

    public String makeHTML() {
       StringBuilder stringBuffer=new StringBuilder();
       stringBuffer.append("<li>\n");
       stringBuffer.append(caption).append("\n");
       stringBuffer.append("<ul>\n");
        for (Object aTray : tray) {
            Item item = (Item) aTray;
            stringBuffer.append(item.makeHTML());
        }
        stringBuffer.append("</ul>\n");
        stringBuffer.append("</li>\n");
        return stringBuffer.toString();
    }
}
