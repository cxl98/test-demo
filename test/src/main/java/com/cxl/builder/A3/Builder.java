package com.cxl.builder.A3;

public abstract class Builder {
    public abstract void makeTitle(String title);

    public abstract void makeString(String cxl);

    public abstract void makeItem(String[] strings);

    public abstract void close();
}
