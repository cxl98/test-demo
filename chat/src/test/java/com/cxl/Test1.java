package com.cxl;

import java.util.Arrays;

public class Test1 {
    private int[] storage;
    private int capacity;
    private int count;
    private static final int GROW_FACTOR=2;

    public Test1() {
        this.capacity=8;
        this.storage=new int[8];
        this.count=0;
    }
    public Test1(int initialCapacity){
        if (initialCapacity<1){
            throw new IllegalArgumentException("Capacity too small");
        }
        this.capacity=initialCapacity;
        this.storage=new int[initialCapacity];
        this.count=0;
    }

    public void push(int value){
        if (count==capacity){
            ensureCapacity();
        }
        storage[count++]=value;
    }

    private void ensureCapacity() {
        int newCapacity=capacity*GROW_FACTOR;
        storage= Arrays.copyOf(storage,newCapacity);
        capacity=newCapacity;
    }
    private int pop(){
        count--;
        if (count==-1){
            throw new IllegalArgumentException("Stack is empty");
        }
        return storage[count];
    }
    private int peek(){
        if (count==0) {
            throw new IllegalArgumentException("Stack is empty");
        }else{
            return storage[count-1];
        }
    }

    private boolean isEmpty(){
        return count==0;
    }
    private int size(){
        return count;
    }

    public static void main(String[] args) {
        Test1 test1=new Test1(3);
        test1.push(1);
        test1.push(2);
        test1.push(3);
        test1.push(4);
        test1.push(5);
        test1.push(6);
        test1.push(7);
        test1.push(8);
        System.out.println(test1.peek());
        System.out.println(test1.size());
        for (int i = 0; i < 8; i++) {
            System.out.println(test1.pop());
        }
        System.out.println(test1.isEmpty());
    }
}
