package com.cxl.composite.A2;

import com.cxl.composite.A1.FileTreatementException;

public abstract class Entry {
    protected Entry parent;
    public void printList(){
        printList("");
    }

    protected abstract void printList(String prefix);
    public Entry add(Entry entry)throws FileTreatementException{
        throw new FileTreatementException();
    }
    public abstract String getName();
    public abstract int getSize();
    public String toString(){
        return getName()+"("+getSize()+")";
    }
    public String getFullName(){
        StringBuffer buffer=new StringBuffer();
        Entry entry=this;
        while (entry!=null){
            buffer.insert(0,"/"+entry.getName());
            entry=entry.parent;
        }
        return buffer.toString();
    }

    public abstract String findFullName(String filename);

}
