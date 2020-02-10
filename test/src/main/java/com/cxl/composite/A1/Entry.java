package com.cxl.composite.A1;

public abstract class Entry {
    protected String name;


    public Entry(String name) {
        this.name = name;
    }

    public abstract String getName();

    public abstract int getSize();

    public void printList(){
        printList("");
    }

    protected abstract void printList(String prefix);
    public Entry add(Entry entry) throws FileTreatementException {
        throw new FileTreatementException();
    }
    public String toString(){
        return getName()+"("+getSize()+")";
    }
}
