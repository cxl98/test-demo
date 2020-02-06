package com.cxl.abstractfactory.tablefactory;

import com.cxl.abstractfactory.factory.Item;
import com.cxl.abstractfactory.factory.Page;

public class TablePage extends Page {
    TablePage(String title, String author) {
        super(title, author);
    }

    protected String makeHTML() {
        StringBuilder builder=new StringBuilder();
        builder.append("<!DOCTYPE html>");
        builder.append("<html lang=\"zh-CN\"><head><meta charset=\"UTF-8\"><title>").append(title).append("</title></head>");
        builder.append("<body>\n");
        builder.append("<h1>").append(title).append("</h1>\n");
        builder.append("<table width=\"80%\" border=\"3\">\n");
        for (Object items: content) {
            Item item= (Item) items;
            builder.append("<tr>").append(item.makeHTML()).append("</tr>");
        }
        builder.append("</table>\n");
        builder.append("<hr><address>").append(author).append("</address>");
        builder.append("</body></html>\n");
        return builder.toString();
    }
}
