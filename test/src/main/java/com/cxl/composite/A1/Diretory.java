package com.cxl.composite.A1;

import java.util.ArrayList;
import java.util.Iterator;

public class Diretory extends Entry {
    private ArrayList<Entry> directory=new ArrayList<Entry>();
    public Diretory(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size=0;
        for (Entry entry : directory) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public Entry add(Entry entry) throws FileTreatementException {
        directory.add(entry);
        return this;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix+"/"+this);
        Iterator<Entry> iterator=directory.iterator();
        while (iterator.hasNext()) {
            Entry entry= iterator.next();
            entry.printList(prefix+"/"+name);
        }
    }
}
