package com.cxl.abstractfactory.tablefactory;

import com.cxl.abstractfactory.factory.Item;
import com.cxl.abstractfactory.factory.Tray;

public class TableTray extends Tray {
    TableTray(String caption) {
        super(caption);
    }

    public String makeHTML() {
        StringBuilder buffer=new StringBuilder();
//        buffer.append("<table width=\"80%\" border=\"3\">\n");
        buffer.append("<td>");
        buffer.append(" <table width=\"100%\" border=\"1\">\n" + "<tr>\n");
        buffer.append("<td bgcolor=\"#cccccc\" align=\"center\" colspan=\""+tray.size()+"\"><b>").append(caption).append("</b></td>\n"+"</tr>");
        buffer.append("<tr>\n");
        for (Object items: tray) {
            Item item= (Item) items;
            buffer.append(item.makeHTML());
        }
        buffer.append("</tr>");
        buffer.append("</table>\n"+"</td>");
        return buffer.toString();
    }
}
