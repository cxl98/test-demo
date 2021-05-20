package com.cxl.algorithm;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LRU {
    class Node {
        private int key;
        private int value;
        private Node pre;
        private Node cur;
    }
    private ConcurrentMap<Integer,Node> cache=new ConcurrentHashMap<>();
    private int count;
    private int cap;
    private Node head,tail;

    public LRU(int cap) {
        this.cap = cap;
        this.count=0;
        head=new Node();
        head.pre=null;
        tail=new Node();
        tail.pre=null;
        head.cur=tail;
        tail.pre=head;
    }
    public int  get(int key){
        Node node = cache.get(key);
        if (node==null){
            return -1;
        }
        insertToHead(node);
        return node.value;
    }

    public void put(int key,int value){
        Node node = cache.get(key);
        if (node!=null){
            node.value=value;
            insertToHead(node);
        }
        Node newNode = new Node();
        newNode.key=key;
        newNode.value=value;
        cache.put(key,newNode);
        addNode(newNode);
        count++;
        if (cap<count){
            Node tail = popTail();
            cache.remove(tail.key);
            count--;
        }
    }

    private Node popTail() {
        Node pre = tail.pre;
        removeNode(pre);
        return pre;
    }

    private void removeNode(Node pre) {
        Node pre1 = pre.pre;
        Node cur = pre.cur;
        pre1.cur=cur;
        cur.pre=pre1;
    }

    private void addNode(Node newNode) {
        newNode.pre=head;
        newNode.cur=head.cur;
        head.cur.pre=newNode;
        head.cur=newNode;
    }

    private void insertToHead(Node node) {
        removeNode(node);
        addNode(node);
    }
}
