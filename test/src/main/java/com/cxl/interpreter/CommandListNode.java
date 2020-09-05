package com.cxl.interpreter;

import java.util.ArrayList;

public class CommandListNode implements Node{
    private ArrayList list=new ArrayList();
    @Override
    public void parse(Context context) throws ParseException {
        while(true){
            if ("end".equals(context.getCurrentToken())){
                context.checkToken("end");
                break;
            }else if (null==context.getCurrentToken()){
              throw new ParseException("没有结束标签");
            } else {
                Node commandNode=new CommandNode();
                commandNode.parse(context);
                list.add(commandNode);
            }
        }
    }

    @Override
    public String toString() {
        return "CommandListNode{" +
                "list=" + list.toString() +
                '}';
    }
}
