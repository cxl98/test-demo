package com.cxl.abstractfactory.listfactory;

import com.cxl.abstractfactory.factory.Item;
import com.cxl.abstractfactory.factory.Page;

public class ListPage extends Page {
     ListPage(String title, String author) {
        super(title, author);
    }

    protected String makeHTML() {
        StringBuilder buffer=new StringBuilder();
        buffer.append("<!DOCTYPE html>");
        buffer.append("<html lang=\"zh-CN\"><head><meta charset=\"UTF-8\"><title>").append(title).append("</title></head>");
        buffer.append("<body>\n");
        buffer.append("<h1>"+title+"</h1>\n");
        buffer.append("<ul>\n");
        for (Object aContent : content) {
            Item item = (Item) aContent;
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("<hr><address>" + author + "</address>");
        buffer.append("</body></html>\n");
        return buffer.toString();
    }
}
