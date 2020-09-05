package com.cxl.interpreter;

public class ProgramNode implements Node{
    private Node commandListNode;
    @Override
    public void parse(Context context) throws ParseException {
        context.checkToken("program");
        commandListNode=new CommandListNode();
        commandListNode.parse(context);
    }

    @Override
    public String toString() {
        return "ProgramNode{" +
                "commandListNode=" + commandListNode +
                '}';
    }
}
