package com.cxl.abstractfactory.factory;

public abstract class Factory {
    private static Factory factory=null;
    public static Factory getFactory(String className){
        try {
            factory= (Factory) Class.forName(className).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factory;
    }
    public abstract Link createLink(String caption,String url);
    public abstract Tray createTray(String caption);
    public abstract Page createPage(String title,String author);
}
