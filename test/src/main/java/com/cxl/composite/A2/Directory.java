package com.cxl.composite.A2;

import java.util.ArrayList;

public class Directory extends Entry {
    private String name;
    private ArrayList<Entry> arrayList=new ArrayList<>();
    public Directory(String name) {
        this.name=name;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix+"/"+this);
        for (Entry entry : arrayList) {
            entry.printList(prefix + "/" + name);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size=0;
        for (Entry entry : arrayList) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry)  {
        arrayList.add(entry);
        entry.parent=this;
        return this;
    }

    @Override
    public String findFullName(String filename) {
        String name = getName();
        System.out.println("Directory  "+name);
        if (filename.equals(this.name)) {
        return getFullName();
        }
        return "没有该文件或文件夹";
    }
}
