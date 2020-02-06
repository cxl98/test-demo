package com.cxl.abstractfactory.tablefactory;

import com.cxl.abstractfactory.factory.Link;

public class TableLink extends Link {
    TableLink(String caption, String url) {
        super(caption, url);
    }

    public String makeHTML() {
        return "<td><a href=\""+url+"\">"+caption+"</a></td>\n";
    }
}
