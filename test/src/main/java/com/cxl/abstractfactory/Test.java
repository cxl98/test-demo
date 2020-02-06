package com.cxl.abstractfactory;

import com.cxl.abstractfactory.factory.Factory;
import com.cxl.abstractfactory.factory.Link;
import com.cxl.abstractfactory.factory.Page;
import com.cxl.abstractfactory.factory.Tray;

public class Test {
    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        String s=scanner.next();
        Factory factory=Factory.getFactory("com.cxl.abstractfactory.tablefactory.TableFactory");
        Link people=factory.createLink("人民日报","http://www.people.com.cn/");
        Link gmw=factory.createLink("光明日报", "http://www.gmw.cn/");
        Link us_yahoo = factory.createLink("Yahoo", "http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("Yahoo,Japan", "http://www.yahoo.co.jp/");
        Link excite = factory.createLink("Excite", "http://www.excite.com/");
        Link google = factory.createLink("Google", "http://www.google.com/");
        Tray traynews = factory.createTray("日报");
        traynews.add(people);
        traynews.add(gmw);

        Tray trayyahoo = factory.createTray("Yahoo!");
        trayyahoo.add(us_yahoo);
        trayyahoo.add(jp_yahoo);

        Tray traysearch = factory.createTray("检索引擎");
        traysearch.add(trayyahoo);
        traysearch.add(excite);
        traysearch.add(google);

        Page page = factory.createPage("LinkPage", "杨文轩");
        page.add(traynews);
        page.add(traysearch);
        page.outPut();
    }
}
