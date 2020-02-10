package com.cxl.composite.A1;

public class File extends Entry {

    private int size;

    public File(String name, int size) {
        super(name);
        this.size=size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
    }
}
