package com.cxl.abstractfactory.tablefactory;

import com.cxl.abstractfactory.factory.Factory;
import com.cxl.abstractfactory.factory.Link;
import com.cxl.abstractfactory.factory.Page;
import com.cxl.abstractfactory.factory.Tray;

public class TableFactory extends Factory {
    public Link createLink(String caption, String url) {
        return new TableLink(caption,url);
    }

    public Tray createTray(String caption) {
        return new TableTray(caption);
    }

    public Page createPage(String title, String author) {
        return new TablePage(title,author);
    }
}
