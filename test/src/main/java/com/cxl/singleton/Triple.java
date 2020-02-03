package com.cxl.singleton;

public class Triple {
    private static Triple[] triples=new Triple[]{
            new Triple(0),
            new Triple(1),
            new Triple(2)

    };
    private int id;
    private Triple(int id){
        System.out.println("创建一个id为："+id+"的实例");
        this.id=id;
    }

    public static Triple getInstance(int id) {
        return triples[id];
    }

    @Override
    public String toString() {
        return "[triple id ="+id+
    "]";
    }
}
