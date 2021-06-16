package com.cxl.algorithm;

import java.util.Stack;

/**
 * @author cxl
 */
public class TOWStack_Queue {
    private Stack<Integer> stack=new Stack<>();
    private Stack<Integer> stack1=new Stack<>();
    public TOWStack_Queue() {
    }
    public void appendTail(int value){
        stack.push(value);
    }
    public int deleteHead(){
        while (!stack.isEmpty()){
            stack1.push(stack.pop());
        }
        if (stack1.isEmpty()) {
            return -1;
        }
        stack1.pop();
        return stack1.size();
    }
}
