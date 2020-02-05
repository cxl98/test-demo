package com.cxl.abstractfactory.listfactory;

import com.cxl.abstractfactory.factory.Link;

public class ListLink extends Link {
     ListLink(String caption, String url) {
        super(caption, url);
    }

    public String makeHTML() {
        return "  <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
