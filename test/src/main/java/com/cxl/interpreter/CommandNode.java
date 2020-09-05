package com.cxl.interpreter;

public class CommandNode implements Node {
    private Node node;
    @Override
    public void parse(Context context) throws ParseException {
        if ("process".equals(context.getCurrentToken())){
            node=new ProcessCommandNode();
            node.parse(context);
        }else{
            node=new ExecuteCommandNode();
            node.parse(context);
        }
    }

    @Override
    public String toString() {
        return "CommandNode{" +
                "node=" + node.toString() +
                '}';
    }
}
