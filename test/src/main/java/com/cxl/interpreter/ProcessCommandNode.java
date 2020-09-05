package com.cxl.interpreter;

public class ProcessCommandNode implements Node{
    private int number;
    private Node commandListNode;
    @Override
    public void parse(Context context) throws ParseException {
        context.checkToken("process");
        number=context.currNumber();
        context.nextToken();
        commandListNode=new CommandListNode();
        commandListNode.parse(context);
    }

    @Override
    public String toString() {
        return "ProcessCommandNode{" +
                "number=" + number +
                ", commandListNode=" + commandListNode +
                '}';
    }
}
