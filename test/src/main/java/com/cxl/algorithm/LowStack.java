package com.cxl.algorithm;

import java.util.Stack;

public class LowStack {
    private Stack<Integer> stack;
    private int min;

    public LowStack() {
        stack=new Stack<>();
        min=Integer.MAX_VALUE;
    }
    public void push(int val){
        if (val<=min){
            stack.push(min);
            min=val;
        }
        stack.push(val);
    }
    public void pop(){
        if (min==stack.peek()){
            stack.pop();
            min=stack.pop();
        }else{
            stack.pop();
        }
        if (stack.isEmpty()){
            min=Integer.MAX_VALUE;
        }
    }
    public int top(){
        return stack.peek();
    }
    public int getMin(){
        return min;
    }
}
