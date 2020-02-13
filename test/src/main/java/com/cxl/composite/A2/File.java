package com.cxl.composite.A2;

public class File extends Entry {
    private int size;
    private String name;
    public File(String name,int size) {
        this.size=size;
        this.name=name;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix+"/"+this);
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
    public String findFullName(String filename) {
        String name = getName();
        System.out.println("file   "+name);
        if (filename.equals(this.name)){
            return getFullName();
        }
        return "没有该文件或文件夹";
    }
}
