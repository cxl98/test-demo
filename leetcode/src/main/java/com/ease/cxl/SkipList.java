package com.ease.cxl;

public class SkipList {
    class Node<T>{
        private T data;
        private Node forwards[]=new Node[MAX_LEVEL];
        private int maxLevel=0;
    }
    private static final float SKIPLIST_P=0.5f;
    private static final int MAX_LEVEL=16;
    private int levelCount=1;
    private  Node head=new Node();
}
